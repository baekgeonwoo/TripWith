package com.janggoback.tripwith.triprequest.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class TripRequestException extends RuntimeException{
	public final Map<String, String> validation = new HashMap<>();

	public TripRequestException(String message) {
		super(message);
	}
	
	public TripRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
