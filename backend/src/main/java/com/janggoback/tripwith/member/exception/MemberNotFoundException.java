package com.janggoback.tripwith.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {

	private static final String MESSAGE = "회원이 존재하지 않습니다.";

	public MemberNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}

}
