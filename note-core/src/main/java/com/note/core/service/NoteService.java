package com.note.core.service;

import com.note.core.service.model.Note;

public interface NoteService {
	
	Note getNote(Long noteId);
	Note upsertNote(Note note);
	void deleteNode(Long noteId);
	

}
