package com.janggoback.tripwith.trip.exception;

import org.springframework.http.HttpStatus;

public class TripNotFoundException extends TripException{
	private static final String MESSAGE = "여행 모집글이 존재하지 않습니다.";
	public TripNotFoundException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

}
