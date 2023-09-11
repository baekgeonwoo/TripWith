package com.janggoback.tripwith.attraction.exception;

import org.springframework.http.HttpStatus;

public class AttractionNotFoundException extends AttractionException {
	private static final String MESSAGE = "글이 존재하지 않습니다.";
	public AttractionNotFoundException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
