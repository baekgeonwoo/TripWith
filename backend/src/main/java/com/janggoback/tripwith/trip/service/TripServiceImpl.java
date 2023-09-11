package com.janggoback.tripwith.trip.service;

import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.exception.TripNotFoundException;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.trip.request.TripEdit;
import com.janggoback.tripwith.trip.response.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripServiceImpl implements TripService{
	private final TripRepository tripRepository;
	private final MemberRepository memberRepository;

	@Override
	public Trip create(TripCreate tripCreate, String email) {
		Member host = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
		return tripRepository.save(tripCreate.toTrip(host));
	}

	@Override
	public void delete(Long id, String email) {
		Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
		if (!trip.getHost().getEmail().equals(email)) {
			throw new UnAuthorizationException();
		}

		tripRepository.deleteById(id);
	}
	@Override
	public TripResponse get(Long id) {
		Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
		trip.view();
		return TripResponse.from(trip);
		
	}
	
	@Override
	public Trip edit(Long id, TripEdit tripEdit, String email) {
		Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
		if (!trip.getHost().getEmail().equals(email)) {
			throw new UnAuthorizationException();
		}

		return tripEdit.toEditedTrip(trip, tripEdit);
	}

	@Override
	public Page<TripResponse> getList(Pageable pageable) {
		return TripResponse.from(tripRepository.findAll(pageable));
	}

	@Override
	public Page<TripResponse> getListByMemberId(Long memberId, Pageable pageable) {
		return tripRepository.findByHostId(memberId, pageable).map(TripResponse::from);
	}
}
