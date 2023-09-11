package com.janggoback.tripwith.sido.exception;

import org.springframework.http.HttpStatus;

public class SidoNotFoundException extends AreaCodeException{
	private static final String MESSAGE = "지역코드가 존재하지 않습니다.";
	public SidoNotFoundException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
