package com.janggoback.tripwith.triprequest.exception;

import org.springframework.http.HttpStatus;

public class LimitExceededException extends TripRequestException{
	private static final String MESSAGE = "인원이 초과 됐습니다.";
	public LimitExceededException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
