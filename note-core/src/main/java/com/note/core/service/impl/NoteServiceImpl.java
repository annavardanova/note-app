package com.note.core.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.note.core.dal.entity.NoteEntity;
import com.note.core.dal.repository.NoteRepository;
import com.note.core.service.NoteService;
import com.note.core.service.model.Note;
import com.note.core.util.datetime.ZonedDateTimeUtil;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	@Qualifier("coreModelMapper")
	private ModelMapper modelMapper;

	@Override
	public Optional<Note> getNote(Long noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note createNote(Note note) {
		note.setCreatedDate(ZonedDateTimeUtil.getUTCNow());
		note.setUpdatedDate(note.getCreatedDate());
		
		NoteEntity noteEntity = modelMapper.map(note, NoteEntity.class);
		noteEntity = noteRepository.save(noteEntity);
		note = modelMapper.map(noteEntity, Note.class);
		return note;
	}

	@Override
	public Note updateNote(Note note) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNode(Long noteId) {
		// TODO Auto-generated method stub

	}

}
