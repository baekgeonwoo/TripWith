package com.janggoback.tripwith.trip.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class TripException extends RuntimeException{
	public final Map<String, String> validation = new HashMap<>();

	public TripException(String message) {
		super(message);
	}
	
	public TripException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
