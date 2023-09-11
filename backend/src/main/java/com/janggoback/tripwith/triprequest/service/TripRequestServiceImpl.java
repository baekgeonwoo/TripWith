package com.janggoback.tripwith.triprequest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.exception.TripNotFoundException;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.triprequest.domain.TripRequest;
import com.janggoback.tripwith.triprequest.exception.DuplicateRequestException;
import com.janggoback.tripwith.triprequest.exception.LimitExceededException;
import com.janggoback.tripwith.triprequest.exception.TripRequestNotFoundException;
import com.janggoback.tripwith.triprequest.repository.TripRequestRepository;
import com.janggoback.tripwith.triprequest.request.TripRequestCreate;
import com.janggoback.tripwith.triprequest.response.TripRequestResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripRequestServiceImpl implements TripRequestService{
	private final TripRequestRepository tripRequestRepository;
	private final TripRepository tripRepository;
	private final MemberRepository memberRepository;

	@Override
	public TripRequest create(String requesterEmail, TripRequestCreate TripRequestCreate) {
		Member requester = memberRepository.findByEmail(requesterEmail).orElseThrow(MemberNotFoundException::new);
		Trip trip = tripRepository.findById(TripRequestCreate.getTripId()).orElseThrow(TripNotFoundException::new);
		if (tripRequestRepository.findByTripIdAndRequesterId(trip.getId(), requester.getId()).isPresent()) {
			throw new DuplicateRequestException();
		}
		
		return tripRequestRepository.save(TripRequestCreate.toTripRequest(trip, requester));
	}

	@Override
	public TripRequest get(Long id) {
		return tripRequestRepository.findById(id).orElseThrow(TripNotFoundException::new);
	}

	@Override
	@Transactional
	public void accept(Long requestId) {
		TripRequest tripRequest = tripRequestRepository.findById(requestId).orElseThrow(TripRequestNotFoundException::new);
		Trip trip = tripRepository.findById(tripRequest.getTrip().getId()).orElseThrow(TripNotFoundException::new);

		if(trip.getAccepted() == trip.getLimitGuest()){
			throw new LimitExceededException();
		}
		trip.accept(); // setter로 값변경하지 말고 entity에 메소드 만들어서 값 변경해야 됨 이유는 몰라
		tripRequest.accept();
	}

	@Override
	@Transactional
	public void reject(Long requestId) {
		TripRequest tripRequest = tripRequestRepository.findById(requestId).orElseThrow(TripRequestNotFoundException::new);

		tripRequest.reject();
	}

	@Override
	public List<TripRequestResponse> getByRequesterEmail(String email) {
		return tripRequestRepository.findByRequesterEmail(email).stream().map(TripRequestResponse::from).collect(Collectors.toList());
	}

	@Override
	public List<TripRequestResponse> getByTripId(Long tripId, String email) {
		Trip findTrip = tripRepository.findById(tripId).orElseThrow(TripNotFoundException::new);
		if(!findTrip.getHost().getEmail().equals(email)){
			throw new UnAuthorizationException();
		}

		return tripRequestRepository.findByTripId(tripId).stream().map(TripRequestResponse::from).collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		tripRequestRepository.deleteById(id);
	}
}
