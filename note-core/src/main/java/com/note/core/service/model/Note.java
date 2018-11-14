package com.note.core.service.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String note;
	private ZonedDateTime createdDate;
	private ZonedDateTime updatedDate;
	
	private Long userId;
	

	public Note() {
		super();
	}

	public Note(String title, String note) {
		super();
		this.title = title;
		this.note = note;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public ZonedDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(ZonedDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	
	

}
