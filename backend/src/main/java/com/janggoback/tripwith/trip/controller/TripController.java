package com.janggoback.tripwith.trip.controller;

import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.trip.request.TripEdit;
import com.janggoback.tripwith.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {
	private final TripService tripService;

	@GetMapping
	public ResponseEntity<?> getTripList(Pageable pageable){
		log.info("getTripList()호출");
		return ResponseEntity.ok(tripService.getList(pageable));
	}

	@PostMapping("/auth")
	public ResponseEntity<?> createTrip(@RequestBody TripCreate tripCreate, @AuthenticationPrincipal UserDetails userDetails){
		tripService.create(tripCreate, userDetails.getUsername());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/members/{member_id}")
	public ResponseEntity<?> getTripsByMemberId(@PathVariable("member_id") Long memberId, Pageable pageable) {
		log.info("getTripsByMemberId() called");
		return ResponseEntity.ok(tripService.getListByMemberId(memberId, pageable));
	}

	@GetMapping("/{trip_id}")
	public ResponseEntity<?> getTrip(@PathVariable("trip_id") Long tripId){
		return ResponseEntity.ok(tripService.get(tripId));
	}

	@PutMapping("/auth/{trip_id}")
	public ResponseEntity<?> updateTrip(@PathVariable("trip_id") Long tripId, @RequestBody TripEdit tripEdit, @AuthenticationPrincipal UserDetails userDetails){
		tripService.edit(tripId, tripEdit, userDetails.getUsername());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/auth/{trip_id}")
	public ResponseEntity<?> delete(@PathVariable("trip_id") Long tripId, @AuthenticationPrincipal UserDetails userDetails){
		tripService.delete(tripId, userDetails.getUsername());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
