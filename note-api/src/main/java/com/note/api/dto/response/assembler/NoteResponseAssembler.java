package com.note.api.dto.response.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.note.api.controller.NoteController;
import com.note.api.controller.UserController;
import com.note.api.dto.request.NoteRequest;
import com.note.api.dto.response.NoteResponse;
import com.note.core.service.model.Note;

public class NoteResponseAssembler extends ResourceAssemblerSupport<Note, NoteResponse> {

	ModelMapper modelMapper;
	public NoteResponseAssembler(ModelMapper modelMapper) {
		super(NoteController.class, NoteResponse.class);
		this.modelMapper = modelMapper;
	}
	@Override
	public NoteResponse toResource(Note entity) {
		NoteResponse note = modelMapper.map(entity, NoteResponse.class);
		note.add(linkTo(methodOn(NoteController.class).getNote(entity.getId())).withSelfRel());
		note.add(linkTo(methodOn(NoteController.class).getNote(entity.getId())).withRel("note/get"));
		note.add(linkTo(methodOn(NoteController.class).deleteNote(entity.getId())).withRel("note/delete"));
		note.add(linkTo(methodOn(NoteController.class).editNote(entity.getId(), new NoteRequest())).withRel("note/edit"));
		note.add(linkTo(methodOn(UserController.class).getUser(entity.getUserId())).withRel("user/get"));

		return note;

	}

}
