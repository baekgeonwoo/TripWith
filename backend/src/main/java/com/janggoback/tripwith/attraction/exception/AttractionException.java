package com.janggoback.tripwith.attraction.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class AttractionException extends RuntimeException {
	public final Map<String, String> validation = new HashMap<>();

	public AttractionException(String message) {
		super(message);
	}
	
	public AttractionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
