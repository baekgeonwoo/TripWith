package com.janggoback.tripwith.board.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class BoardException extends RuntimeException {
	public final Map<String, String> validation = new HashMap<>();

	public BoardException(String message) {
		super(message);
	}
	
	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract int getStatusCode();
	
	public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
