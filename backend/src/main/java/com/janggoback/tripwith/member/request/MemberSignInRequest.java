package com.janggoback.tripwith.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignInRequest {

	@Email(message = "이메일 형식에 맞지 않습니다.")
	@NotBlank(message = "이메일은 필수 입력사항 입니다.")
	private String email;
	@NotBlank(message = "이메일은 필수 입력사항 입니다.")
	private String password;
}
