package com.note.api.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class NoteRequest implements Serializable {
		private static final long serialVersionUID = 1L;

		@Size(min=1, max=50)
		private String title;
		
		@Size(max=1000)
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
