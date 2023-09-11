package com.janggoback.tripwith.auth.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizationException extends  AuthException{
    private static final String MESSAGE = "권한이 없습니다.";
    public UnAuthorizationException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
