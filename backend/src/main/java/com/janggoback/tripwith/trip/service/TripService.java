package com.janggoback.tripwith.trip.service;

import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.trip.request.TripEdit;
import com.janggoback.tripwith.trip.response.TripResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {
	Trip create(TripCreate tripCreate, String email);
	void delete(Long id, String email);
	TripResponse get(Long id);
	Trip edit(Long id, TripEdit tripEdit, String email);
	Page<TripResponse> getList(Pageable pageable);
	Page<TripResponse> getListByMemberId(Long memberId, Pageable pageable);
}

