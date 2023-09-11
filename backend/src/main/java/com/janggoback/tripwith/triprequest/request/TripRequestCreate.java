package com.janggoback.tripwith.triprequest.request;

import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.triprequest.domain.TripRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TripRequestCreate {
	private String content;
	private Long tripId;
	
	@Builder
	public TripRequestCreate(String content, Long tripId) {
		this.content = content;
		this.tripId = tripId;
	}
	
	public TripRequest toTripRequest(Trip trip, Member requester) {
		return TripRequest.builder()
				.content(this.content)
				.trip(trip)
				.requester(requester)
				.build();
	}
}
