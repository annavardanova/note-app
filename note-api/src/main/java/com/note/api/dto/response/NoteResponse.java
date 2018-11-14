package com.note.api.dto.response;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class NoteResponse extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;


	private String title;
	private String note;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
