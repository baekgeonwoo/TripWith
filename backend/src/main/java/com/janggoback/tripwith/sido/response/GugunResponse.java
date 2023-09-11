package com.janggoback.tripwith.sido.response;

import com.janggoback.tripwith.sido.domain.Gugun;
import com.janggoback.tripwith.sido.domain.Sido;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GugunResponse {
	private String gugunName;
	private Long gugunCode; //강남구(1)
	private Sido sido;
	
	@Builder
	public GugunResponse(String gugunName, Long gugunCode, Sido sido) {
		super();
		this.gugunName = gugunName;
		this.gugunCode = gugunCode;
		this.sido = sido;
	}
	
	public static GugunResponse from(Gugun gugun) {
		return GugunResponse.builder()
				.gugunName(gugun.getGugunName())
				.gugunCode(gugun.getGugunCode())
				.sido(gugun.getSido())
				.build();
	}
	
}
