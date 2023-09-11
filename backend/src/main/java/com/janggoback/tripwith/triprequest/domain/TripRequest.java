package com.janggoback.tripwith.triprequest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.trip.domain.Trip;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripRequest {
	@Id
	@Column(name="trip_request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;

	private Status status;

	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="trip_id")
	private Trip trip;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name= "member_id")
	private Member requester;
	
	@Builder
	public TripRequest(String content, Trip trip, Member requester) {
		this.content = content;
		this.status = Status.WAIT; // 초기는 보류상태
		this.trip = trip;
		this.requester = requester;
	}

	public void accept(){
		this.status = Status.ACCEPT;
	}

	public void reject(){
		this.status = Status.REJECT;
	}
}
