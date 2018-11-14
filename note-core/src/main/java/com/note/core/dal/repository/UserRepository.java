package com.note.core.dal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.note.core.dal.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);

}
