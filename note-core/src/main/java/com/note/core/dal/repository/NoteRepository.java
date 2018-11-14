package com.note.core.dal.repository;

import org.springframework.data.repository.CrudRepository;

import com.note.core.dal.entity.NoteEntity;

public interface NoteRepository extends CrudRepository<NoteEntity, Long> {
	

}
