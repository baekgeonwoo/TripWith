package com.janggoback.tripwith.triprequest.exception;

import org.springframework.http.HttpStatus;

public class DuplicateRequestException extends TripRequestException {
	private static final String MESSAGE = "이미 존재하는 동행 신청입니다.";
	public DuplicateRequestException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
