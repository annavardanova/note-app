package com.note.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.api.dto.request.NoteRequest;
import com.note.api.dto.response.NoteResponse;
import com.note.api.dto.response.assembler.NoteResponseAssembler;
import com.note.core.service.NoteService;
import com.note.core.service.exception.NoteNotFoundException;
import com.note.core.service.model.Note;

@RestController
@RequestMapping(value="/notes", produces = "application/hal+json")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	@Autowired
	@Qualifier("apiModelMapper")
	private ModelMapper modelMapper;
	

	@GetMapping(path="/{id}")
	public ResponseEntity<NoteResponse> getNote(@PathVariable("id") Long id) {
		Note note = note = noteService.getNote(id).orElseThrow(NoteNotFoundException::new);
		NoteResponseAssembler noteResourceAssembler = new NoteResponseAssembler(modelMapper);
		return new ResponseEntity<NoteResponse>(noteResourceAssembler.toResource(note), HttpStatus.OK);
	};
	
	
	@PostMapping("/")
	public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest noteModel) {
		Note note = modelMapper.map(noteModel, Note.class);
		note.setUserId(999L);//TODO:tmp, top be removed
		note = noteService.createNote(note);
		
		NoteResponseAssembler noteResourceAssembler = new NoteResponseAssembler(modelMapper);
		return new ResponseEntity<NoteResponse>(noteResourceAssembler.toResource(note), HttpStatus.OK);
	};

}


