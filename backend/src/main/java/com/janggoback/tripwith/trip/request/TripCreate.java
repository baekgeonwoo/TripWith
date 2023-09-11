package com.janggoback.tripwith.trip.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.trip.domain.Trip;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TripCreate {
	private String title;
	private List<Long> attractionIds;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	private String content;
	private Long limitGuest;
	
	@Builder
	public TripCreate(String title, List<Long> attractionIds, LocalDateTime startDate,
			LocalDateTime endDate, String content, Long limitGuest) {
		this.title = title;
		this.attractionIds = attractionIds;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.limitGuest = limitGuest;
	}
	
	public Trip toTrip(Member host) {
		return Trip.builder()
				.title(this.getTitle())
				.attractionIds(this.attractionIds)
				.startDate(this.getStartDate())
				.endDate(this.getEndDate())
				.content(this.getContent())
				.host(host)
				.limitGuest(this.getLimitGuest())
				.build();
				
	}			
	
}
