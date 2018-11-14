package com.note.core.dal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.note.core.dal.entity.NoteEntity;

public interface NoteRepository extends CrudRepository<NoteEntity, Long> {
	
	Optional<NoteEntity> findByTitle(String title);

}
