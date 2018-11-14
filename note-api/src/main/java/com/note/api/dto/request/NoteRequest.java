package com.note.api.dto.request;

import java.io.Serializable;

public class NoteRequest implements Serializable {
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
