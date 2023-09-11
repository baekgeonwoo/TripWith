package com.janggoback.tripwith.triprequest.response;

import java.time.LocalDateTime;

import com.janggoback.tripwith.triprequest.domain.Status;
import com.janggoback.tripwith.triprequest.domain.TripRequest;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TripRequestResponse {
	private Long id;
	private String content;

	private Status status;
	private LocalDateTime createdAt;
	private Long tripId;
	private Long requesterId;
	
	@Builder
	public TripRequestResponse(Long id, String content, Status status, LocalDateTime createdAt, Long tripId,
			Long requesterId) {
		super();
		this.id = id;
		this.content = content;
		this.status = status;
		this.createdAt = createdAt;
		this.tripId = tripId;
		this.requesterId = requesterId;
	}
	
	public static TripRequestResponse from(TripRequest tripRequest) {
		return TripRequestResponse.builder()
				.id(tripRequest.getId())
				.content(tripRequest.getContent())
				.status(tripRequest.getStatus())
				.createdAt(tripRequest.getCreatedAt())
				.tripId(tripRequest.getTrip().getId())
				.requesterId(tripRequest.getRequester().getId())
				.build();
	}
}
