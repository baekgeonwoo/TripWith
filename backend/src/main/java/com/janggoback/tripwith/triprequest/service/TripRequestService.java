package com.janggoback.tripwith.triprequest.service;

import com.janggoback.tripwith.triprequest.domain.TripRequest;
import com.janggoback.tripwith.triprequest.request.TripRequestCreate;
import com.janggoback.tripwith.triprequest.response.TripRequestResponse;

import java.util.List;

public interface TripRequestService {

	TripRequest get(Long id);

	void accept(Long requestId);

	void reject(Long requestId);

	TripRequest create(String requesterEmail, TripRequestCreate TripRequestCreate);
	List<TripRequestResponse> getByRequesterEmail(String email);
	List<TripRequestResponse> getByTripId(Long tripId, String email);
	void delete(Long id);
}
