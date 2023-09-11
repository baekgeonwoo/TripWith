package com.janggoback.tripwith.triprequest.exception;

import org.springframework.http.HttpStatus;

public class TripRequestNotFoundException extends TripRequestException{
	private static final String MESSAGE = "여행 요청이 존재하지 않습니다.";
	public TripRequestNotFoundException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
