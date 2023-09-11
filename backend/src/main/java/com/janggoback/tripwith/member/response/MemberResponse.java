package com.janggoback.tripwith.member.response;

import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {
    private Long id;	
	@ApiModelProperty(value = "username 겸 email")
	private String email;
    private String name;
    private Integer age;
    @ApiModelProperty(value = "M 또는 F")
    private Gender gender;
    @ApiModelProperty(value = "공공데이터 상의 지역코드")
    private String region;
    
    @Builder
    public MemberResponse(Long id, String email, String name, Integer age, Gender gender, String region) {
    	this.id = id;
		this.email = email;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.region = region;
	}
    
    public static MemberResponse from(Member member) {
    	return MemberResponse.builder()
    			.id(member.getId())
		    	.email(member.getEmail())
		    	.name(member.getName())
		    	.age(member.getAge())
		    	.gender(member.getGender())
		    	.region(member.getRegion())
		    	.build();
    }
}
