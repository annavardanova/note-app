package com.note.core.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.note.core.dal.entity.NoteEntity;
import com.note.core.dal.entity.UserEntity;
import com.note.core.dal.repository.NoteRepository;
import com.note.core.service.NoteService;
import com.note.core.service.UserService;
import com.note.core.service.model.Note;
import com.note.core.service.model.User;
import com.note.core.util.datetime.ZonedDateTimeUtil;

@RunWith(SpringRunner.class)
public class NoteServiceImplTest {

	@TestConfiguration
	public static class NoteServiceImplTestContextConfiguration {
		@Bean
		public NoteService noteService() {
			return new NoteServiceImpl();
		}
	}

	@Autowired
	private NoteService noteService;
	
	@MockBean
	private UserService userService;

	@MockBean
	private NoteRepository noteRepository;
	
	@MockBean(name = "coreModelMapper")
	private ModelMapper coreModelMapper;

	@Test
	public void createNoteTest_ok() {
		// given
		Note note = createNoteTest_ok_setup();

		// when
		Note noteReturned = noteService.createNote(note);

		// then
		assertThat(noteReturned).isNotNull();
		assertThat(noteReturned.getId()).isNotNull();
		assertThat(noteReturned.getTitle()).isEqualTo(note.getTitle());
		assertThat(noteReturned.getNote()).isEqualTo(note.getNote());
	}
	
	@Test
	public void createNoteTest_incompleteDataProvided_fail() {
		
	}
	
	@Test
	public void createNoteTest_repositoryException_fail() {
		
	}

	private Note createNoteTest_ok_setup() {
		NoteEntity noteEntity = new NoteEntity();
		noteEntity.setTitle("Test title");
		noteEntity.setNote("Test note");
		noteEntity.setCreatedDate(ZonedDateTimeUtil.getUTCNow());
		noteEntity.setUpdatedDate(noteEntity.getCreatedDate());
		noteEntity.setId(999L);

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("test@test.com");
		userEntity.setPassword("testpass");
		userEntity.setCreatedDate(ZonedDateTimeUtil.getUTCNow());
		userEntity.setUpdatedDate(noteEntity.getCreatedDate());
		userEntity.setId(999L);
		noteEntity.setUser(userEntity);

		Note note_notSaved = notSavedNote();
		Note note_saved = notSavedNote();
		note_saved.setId(999L);
		note_saved.setCreatedDate(noteEntity.getCreatedDate());
		note_saved.setUpdatedDate(noteEntity.getUpdatedDate());

		Mockito.when(noteRepository.save(noteEntity)).thenReturn(noteEntity);
		
		Mockito.when(userService.getUser(999L)).thenReturn(Optional.of(notSavedUser()));

		Mockito.when(coreModelMapper.map(note_notSaved, NoteEntity.class)).thenReturn(noteEntity);

		Mockito.when(coreModelMapper.map(noteEntity, Note.class)).thenReturn(note_saved);
		
		return note_notSaved;

	}

	Note notSavedNote() {
		Note note = new Note("Test title", "Test note");
		note.setUserId(999L);
		return note;
	}
	
	User notSavedUser() {
		User user = new User();
		user.setId(999L);
		return user;
	}

}
