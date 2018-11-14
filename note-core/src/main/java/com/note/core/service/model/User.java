package com.note.core.service.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String password;
	private ZonedDateTime createdDate;
	private ZonedDateTime updatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	

}
