package com.janggoback.tripwith.auth.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class AuthException extends RuntimeException {
	public final Map<String, String> validation = new HashMap<>();

	public AuthException(String message) {
		super(message);
	}

	public AuthException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
