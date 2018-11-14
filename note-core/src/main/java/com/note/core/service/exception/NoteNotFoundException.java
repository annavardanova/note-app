package com.note.core.service.exception;

public class NoteNotFoundException extends NotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public NoteNotFoundException() {
		super();
	}
	
	public NoteNotFoundException(String message) {
		super(message);
	}
	
	public NoteNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

}
