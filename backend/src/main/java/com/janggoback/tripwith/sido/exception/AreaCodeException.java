package com.janggoback.tripwith.sido.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class AreaCodeException extends RuntimeException{
	public final Map<String, String> validation = new HashMap<>();

	public AreaCodeException(String message) {
		super(message);
	}
	
	public AreaCodeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
