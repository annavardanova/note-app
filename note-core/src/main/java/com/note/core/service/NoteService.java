package com.note.core.service;

import java.util.Optional;

import com.note.core.service.model.Note;

public interface NoteService {
	
	
	Optional<Note> getNote(Long noteId);
	Note createNote(Note note);
	Note updateNote(Note note);
	void deleteNote(Long noteId);
	

}
