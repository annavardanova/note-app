package com.note.core.service.exception;

public class NotFoundException extends NoteAppException {
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, Exception exception) {
		super(message, exception);
	}

}
