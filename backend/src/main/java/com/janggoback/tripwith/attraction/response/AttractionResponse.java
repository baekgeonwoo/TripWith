package com.janggoback.tripwith.attraction.response;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
	private String name;
	private String img1;
	private String img2;
	private String content;
	private Long viewCount;
	private Long areaCode;
	private Long sigunguCode;
	private Long contentTypeId;
	private LocalDateTime modifiedTime;
	private Double longitude;
	private Double latitude;

	@Builder
	public AttractionResponse(Long id, String address, String name, String img1, String img2, String content, Long viewCount, Long areaCode, Long sigunguCode, Long contentTypeId, LocalDateTime modifiedTime, Double longitude, Double latitude) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.img1 = img1;
		this.img2 = img2;
		this.content = content;
		this.viewCount = viewCount;
		this.areaCode = areaCode;
		this.sigunguCode = sigunguCode;
		this.contentTypeId = contentTypeId;
		this.modifiedTime = modifiedTime;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public static AttractionResponse from(Attraction attraction) {
		return AttractionResponse.builder()
				.id(attraction.getId())
				.address(attraction.getAddress())
				.name(attraction.getName())
				.img1(attraction.getImg1())
				.img2(attraction.getImg2())
				.content(attraction.getContent())
				.viewCount(attraction.getViewCount())
				.areaCode(attraction.getAreaCode())
				.sigunguCode(attraction.getSigunguCode())
				.contentTypeId(attraction.getContentTypeId())
				.modifiedTime(attraction.getModifiedTime())
				.longitude(attraction.getLongitude())
				.latitude(attraction.getLatitude())
				.build();
	}
	
}
