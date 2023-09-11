package com.janggoback.tripwith.sido.response;

import com.janggoback.tripwith.sido.domain.Sido;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SidoResponse {
	private String sidoName;
	private Long sidoCode;
	
	@Builder
	public SidoResponse(String sidoName, Long sidoCode) {
		super();
		this.sidoName = sidoName;
		this.sidoCode = sidoCode;
	}
	
	public static SidoResponse from(Sido sido) {
		return SidoResponse.builder()
				.sidoName(sido.getSidoName())
				.sidoCode(sido.getSidoCode())
				.build();
	}
}
