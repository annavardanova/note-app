package com.note.core.dal.repository;

import org.springframework.data.repository.CrudRepository;

import com.note.core.dal.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
