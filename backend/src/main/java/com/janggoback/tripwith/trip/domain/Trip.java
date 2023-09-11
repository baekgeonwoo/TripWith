package com.janggoback.tripwith.trip.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.trip.request.TripEdit;
import com.janggoback.tripwith.triprequest.domain.TripRequest;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@ApiModel
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip {
	@Id @Column(name = "trip_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String content;
	private Long viewCount;

	private Long limitGuest;
	private Long accepted;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "member_id")
	private Member host;

	@JsonManagedReference // 순환참조 방지
	@OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE)
	private List<TripRequest> tripRequests = new ArrayList<>();

	@ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
	private List<Long> attractionIds = new ArrayList<>();


	@Builder
	public Trip(String title, List<Long> attractionIds, LocalDateTime startDate,
			LocalDateTime endDate, String content, Member host, Long limitGuest) {
		this.title = title;
		this.attractionIds = attractionIds;
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
		this.host = host;
		this.limitGuest = limitGuest;
		this.accepted = 1L; // 초기 값은 호스트 1명
		this.viewCount = 0L;
	}

	public void editToTrip(TripEdit tripEdit){
		this.title = tripEdit.getTitle();
		this.attractionIds = tripEdit.getAttractionIds();
		this.startDate = tripEdit.getStartDate();
		this.endDate = tripEdit.getEndDate();
		this.content = tripEdit.getContent();
	}

	public void accept(){
		this.accepted += 1;
	}

	public void view() { this.viewCount += 1;}
}
