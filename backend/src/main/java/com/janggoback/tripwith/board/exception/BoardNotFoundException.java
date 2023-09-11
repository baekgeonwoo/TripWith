package com.janggoback.tripwith.board.exception;

import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends BoardException {
	private static final String MESSAGE = "글이 존재하지 않습니다.";
	public BoardNotFoundException() {
		super(MESSAGE);
	}

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
