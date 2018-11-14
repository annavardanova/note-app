package com.note.core.service.exception;

public class UserNotFoundException extends NotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

}
