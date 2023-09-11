package com.janggoback.tripwith.trip.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.janggoback.tripwith.trip.domain.Trip;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@ApiModel
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripResponse {
	private Long id;
	private String title;
	private List<Long> attractionIds;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String content;
	private Long viewCount;
	private Long limitGuest;
	private Long accepted;
	private LocalDateTime createdAt;
	private Long writerId;
	private Long hostId;
	private List<Long> requestIds;
	
	@Builder
	public TripResponse(Long id, String title,List<Long> attractionIds, LocalDateTime startDate,
			LocalDateTime endDate, String content, Long viewCount, Long limitGuest, Long accepted, LocalDateTime createdAt, Long writerId, Long hostId, List<Long> requestIds) {
		this.id = id;
		this.title = title;
		this.attractionIds = attractionIds;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.viewCount = viewCount;
		this.limitGuest = limitGuest;
		this.accepted = accepted;
		this.createdAt = createdAt;
		this.writerId = writerId;
		this.hostId = hostId;
		this.requestIds = requestIds;
	}
	
	public static TripResponse from(Trip trip) {
		return TripResponse.builder()
				.id(trip.getId())
				.title(trip.getTitle())
				.attractionIds(trip.getAttractionIds())
				.startDate(trip.getStartDate())
				.endDate(trip.getEndDate())
				.content(trip.getContent())
				.viewCount(trip.getViewCount())
				.limitGuest(trip.getLimitGuest())
				.accepted(trip.getAccepted())
				.createdAt(trip.getCreatedAt())
				.writerId(trip.getHost().getId())
				.hostId(trip.getHost().getId())
				.requestIds(trip.getTripRequests().stream().map((request) -> {
					return request.getRequester().getId();
				}).collect(Collectors.toList()))
				.build();
	}

	public static Page<TripResponse> from(Page<Trip> trips) {
		return trips.map(trip ->
				TripResponse.from(trip)
//				TripResponse.builder()
//						.id(trip.getId())
//						.title(trip.getTitle())
//						.attractionIds(trip.getAttractionIds())
//						.startDate(trip.getStartDate())
//						.endDate(trip.getEndDate())
//						.content(trip.getContent())
//						.viewCount(trip.getViewCount())
//						.createdAt(trip.getCreatedAt())
//						.writerId(trip.getHost().getId())
//						.build()
		);
	}
}
