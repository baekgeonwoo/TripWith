package com.janggoback.tripwith.trip.request;

import java.time.LocalDateTime;
import java.util.List;

import com.janggoback.tripwith.trip.domain.Trip;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TripEdit {
	private String title;
	private List<Long> attractionIds;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String content;
	
	@Builder
	public TripEdit(String title, List<Long> attractionIds, LocalDateTime startDate,
			LocalDateTime endDate, String content) {
		this.title = title;
		this.attractionIds = attractionIds;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
	}
	
	public Trip toEditedTrip(Trip trip, TripEdit tripEdit) {
		trip.editToTrip(tripEdit);
		return trip;
	}
}
