package com.note.core.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.note.core.dal.entity.NoteEntity;
import com.note.core.dal.repository.NoteRepository;
import com.note.core.service.NoteService;
import com.note.core.service.UserService;
import com.note.core.service.exception.NoteAppException;
import com.note.core.service.exception.NoteCreationFailedException;
import com.note.core.service.exception.NoteNotFoundException;
import com.note.core.service.exception.UserNotFoundException;
import com.note.core.service.model.Note;
import com.note.core.util.datetime.ZonedDateTimeUtil;

/**
 * Implementation of the NoteService. Throughout its methods, 
 * assertions insure here that the service module is used properly.
 * 
 * Farther improvement: make exceptions messages make more concrete in some cases.
 * @author annavardanova
 *
 */

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	@Qualifier("coreModelMapper")
	private ModelMapper modelMapper;

	@Override
	public Optional<Note> getNote(Long noteId) {
		Assert.notNull(noteId, "Note id cannot be null");
		try {
			Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);
			return noteEntity.map(entity -> modelMapper.map(entity, Note.class));
		}catch(Exception e) {
			throw new NoteAppException("Note retrieval failed", e);
		}
		
	}

	@Override
	public Note createNote(Note note) {
		Assert.notNull(note, "Note cannot be null");
		Assert.hasText(note.getTitle(), "Note title cannot be empty");
		Assert.notNull(note.getUserId(), "Note creating user cannot be null");
		
		try {
			//get and check if the note author user exists. If yes, prepare user ref entity
			//as alternative exists expression could be used
			userService.getUser(note.getUserId()).orElseThrow(UserNotFoundException :: new);
			
			//prepare note entity to be saved
			NoteEntity noteEntity = modelMapper.map(note, NoteEntity.class);//user id is mapped automatically
			noteEntity.setCreatedDate(ZonedDateTimeUtil.getUTCNow());
			noteEntity.setUpdatedDate(noteEntity.getCreatedDate());
			noteEntity = noteRepository.save(noteEntity);
			note = modelMapper.map(noteEntity, Note.class);
			return note;
		}catch(Exception e) {
			throw new NoteCreationFailedException("Note creation failed", e);
		}
		
	}

	@Override
	public Note updateNote(Note note) {
		Assert.notNull(note, "Note cannot be null");
		Assert.notNull(note.getId(), "Note id cannot be null");
		Assert.hasText(note.getTitle(), "Note title cannot be empty");
		
		try {
			//prepare note entity to be saved
			NoteEntity noteEntity = noteRepository.findById(note.getId()).orElseThrow(NoteNotFoundException :: new);
			noteEntity.setUpdatedDate(ZonedDateTimeUtil.getUTCNow());
			noteEntity.setNote(note.getNote());
			noteEntity.setTitle(note.getTitle());
			noteEntity = noteRepository.save(noteEntity);
			note = modelMapper.map(noteEntity, Note.class);
			return note;
		}catch(Exception e) {
			throw new NoteCreationFailedException("Note creation failed", e);
		}
	}

	@Override
	public void deleteNote(Long noteId) {
		Assert.notNull(noteId, "Note id cannot be null");
		try {
			Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);
			noteRepository.delete(noteEntity.orElseThrow(NoteNotFoundException :: new));
		}catch(NoteNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new NoteAppException("Note retrieval failed", e);
		}

	}

}
