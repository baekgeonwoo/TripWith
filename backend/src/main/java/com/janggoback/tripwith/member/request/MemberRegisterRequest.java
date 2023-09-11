package com.janggoback.tripwith.member.request;

import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel
@Getter
@Setter
@NoArgsConstructor // json to MemberRegisterReuquest 하려면 이거 있어야됨
public class MemberRegisterRequest {

	@Email(message = "이메일 형식에 맞지 않습니다.")
	@NotBlank(message = "이메일은 필수 입력사항 입니다.")
	@ApiModelProperty(value = "username 겸 email")
	private String email;

	@NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
	private String password;

	@NotBlank(message = "이름은 필수 입력사항 입니다.")
	private String name;

	@NotBlank(message = "나이는 필수 입력사항 입니다.")
	private Integer age;

	@ApiModelProperty(value = "M 또는 F")
	@NotBlank(message = "성별은 필수 입력사항 입니다.")
	private Gender gender;

	@ApiModelProperty(value = "공공데이터 상의 지역코드")
	@NotBlank(message = "지역은 필수 입력사항 입니다.")
	private String region;

	private Role role;

	@Builder
    public MemberRegisterRequest(String email, String password, String name, Integer age, Gender gender, String region, Role role) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.region = region;
		this.role = role;
	}

}
