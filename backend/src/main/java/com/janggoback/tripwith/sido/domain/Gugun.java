package com.janggoback.tripwith.sido.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gugun {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String gugunName;
	private Long gugunCode; //강남구(1)
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "sido_code")
	private Sido sido;
	
	@Builder
	public Gugun(String gugunName, Long gugunCode) {
		super();
		this.gugunName = gugunName;
		this.gugunCode = gugunCode;
	}
}
