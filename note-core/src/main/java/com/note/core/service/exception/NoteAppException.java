package com.note.core.service.exception;

public class NoteAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoteAppException() {
		super();
	}
	
	public NoteAppException(String message) {
		super(message);
	}
	
	public NoteAppException(String message, Exception exception) {
		super(message, exception);
	}

}

