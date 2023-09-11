package com.janggoback.tripwith.member.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class MemberException extends RuntimeException {
	public final Map<String, String> validation = new HashMap<>();

	public MemberException(String message) {
		super(message);
	}
	
	public MemberException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
