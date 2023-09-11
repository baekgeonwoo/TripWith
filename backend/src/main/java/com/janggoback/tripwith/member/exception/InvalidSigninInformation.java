package com.janggoback.tripwith.member.exception;

import org.springframework.http.HttpStatus;

public class InvalidSigninInformation extends MemberException {

    private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";
    
    public InvalidSigninInformation() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
