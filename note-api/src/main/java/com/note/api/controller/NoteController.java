package com.note.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.api.dto.request.NoteRequest;
import com.note.api.dto.response.NoteResponse;
import com.note.api.dto.response.assembler.NoteResponseAssembler;
import com.note.core.service.NoteService;
import com.note.core.service.UserService;
import com.note.core.service.exception.NoteNotFoundException;
import com.note.core.service.model.Note;
import com.note.core.service.model.User;

@RestController
@RequestMapping(value="/notes", produces = "application/hal+json")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	@Qualifier("apiModelMapper")
	private ModelMapper modelMapper;
	

	/**
	 * The action responsible for note creation
	 */
	@PreAuthorize("hasPermission(#id, 'note', 'READ')")
	@GetMapping(path="/{id}")
	public ResponseEntity<NoteResponse> getNote(@PathVariable("id") Long id) {
		Note note = noteService.getNote(id).orElseThrow(NoteNotFoundException::new);
		NoteResponseAssembler noteResourceAssembler = new NoteResponseAssembler(modelMapper);
		return new ResponseEntity<NoteResponse>(noteResourceAssembler.toResource(note), HttpStatus.OK);
	};
	
	/**
	 * The action handles note creation
	 */
	@PreAuthorize("hasPermission(#id, 'note', 'CREATE')")
	@PostMapping("/")
	public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest noteModel) {
		Note note = modelMapper.map(noteModel, Note.class);
		
		//subject for a separate web util.
		//if custom user details are used in security configuration, no need to retrieve the user object
		//as its id will be available in context.
		String username = ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userService.getUserByUsername(username).get();
		
		note.setUserId(user.getId());//TODO:tmp, top be removed
		note = noteService.createNote(note);
		NoteResponseAssembler noteResourceAssembler = new NoteResponseAssembler(modelMapper);
		return new ResponseEntity<NoteResponse>(noteResourceAssembler.toResource(note), HttpStatus.CREATED);
	};
	
	/**
	 * The action tries to completely update the business note resource which comprises of title and contents
	 */
	@PreAuthorize("hasPermission(#id, 'note', 'EDIT')")
	@PutMapping(path="/{id}")
	public ResponseEntity<NoteResponse> editNote(@PathVariable("id") Long id, @Valid @RequestBody NoteRequest noteModel) {
		Note note = modelMapper.map(noteModel, Note.class);
		note.setId(id);
		note = noteService.updateNote(note);
		
		NoteResponseAssembler noteResourceAssembler = new NoteResponseAssembler(modelMapper);
		return new ResponseEntity<NoteResponse>(noteResourceAssembler.toResource(note), HttpStatus.OK);
	};
	
	/**
	 * The action responsible for note deletion
	 */
	@PreAuthorize("hasPermission(#id, 'note', 'DELETE')")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
		noteService.deleteNote(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	};

}


