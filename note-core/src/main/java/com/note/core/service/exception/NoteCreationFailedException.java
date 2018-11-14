package com.note.core.service.exception;

public class NoteCreationFailedException extends NoteAppException {

	private static final long serialVersionUID = 1L;
	
	public NoteCreationFailedException() {
		super();
	}
	
	public NoteCreationFailedException(String message) {
		super(message);
	}
	
	public NoteCreationFailedException(String message, Exception exception) {
		super(message, exception);
	}

}
