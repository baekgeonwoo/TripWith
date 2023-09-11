package com.janggoback.tripwith.triprequest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janggoback.tripwith.triprequest.request.TripRequestCreate;
import com.janggoback.tripwith.triprequest.service.TripRequestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trip-requests")
@RequiredArgsConstructor
@Slf4j
public class TripRequestController {
	private final TripRequestService tripRequestService;

	@GetMapping("/auth/{trip_request_id}")
	public ResponseEntity<?> getRequest(@PathVariable("trip_request_id") Long requestId, @AuthenticationPrincipal UserDetails userDetails){
		// todo request host, request 작성자, 관리자만 열람 가능하게 인증 정보 추가해야함

		return ResponseEntity.ok(tripRequestService.get(requestId));
	}

	@GetMapping("/auth/{trip_request_id}/accept")
	public ResponseEntity<?> acceptRequest(@PathVariable("trip_request_id") Long requestId, @AuthenticationPrincipal UserDetails userDetails){
		// todo host만 수락 가능

		tripRequestService.accept(requestId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/auth/{trip_request_id}/reject")
	public ResponseEntity<?> rejectRequest(@PathVariable("trip_request_id") Long requestId, @AuthenticationPrincipal UserDetails userDetails){
		// todo host만 거절 가능

		tripRequestService.reject(requestId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/auth/request/{trip_id}") // 동행 요청 생성
	public ResponseEntity<?> createTripRequest(@PathVariable("trip_id") Long tripId, @AuthenticationPrincipal UserDetails userDetails, @RequestBody String content){

		return ResponseEntity.ok(tripRequestService.create(userDetails.getUsername(), TripRequestCreate.builder().tripId(tripId).content(content).build()));
	}

	@GetMapping("/auth/member/{member_id}") // 유저가 신청한 동행 리퀘스트 다 가져 오기
	public ResponseEntity<?> getUserRequest(@PathVariable("member_id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		return ResponseEntity.ok(tripRequestService.getByRequesterEmail(userDetails.getUsername()));
	}

	@GetMapping("/auth/trip/{trip_id}") // 동행에 들어온 모든 여행 신청 파악
	public ResponseEntity<?> getTripRequest(@PathVariable("trip_id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		return ResponseEntity.ok(tripRequestService.getByTripId(id, userDetails.getUsername()));
	}
}
