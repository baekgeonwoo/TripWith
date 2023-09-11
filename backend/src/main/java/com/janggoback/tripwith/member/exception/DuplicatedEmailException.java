package com.janggoback.tripwith.member.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends MemberException {

	private static final String MESSAGE = "이미 존재하는 이메일입니다.";

	public DuplicatedEmailException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}

}
