package com.janggoback.tripwith.attraction.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Entity
@ApiModel
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attraction implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
	private String name;
	private String img1;
	private String img2;
	@Lob
	private String content;
	private Long viewCount;
	private Long areaCode;
	private Long sigunguCode;
	private Long contentTypeId;
	private LocalDateTime modifiedTime;
	private Double longitude;
	private Double latitude;

	@Builder
	public Attraction(String address, String name, String img1, String img2, String content, Long viewCount,
			Long areaCode, Long sigunguCode, Long contentTypeId, LocalDateTime modifiedTime, Double longitude, Double latitude) {
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
	
}
