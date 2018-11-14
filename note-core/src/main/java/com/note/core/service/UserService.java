package com.note.core.service;

import java.util.Optional;

import com.note.core.service.model.User;

public interface UserService {
	
	Optional<User> getUser(Long userId);

	Optional<User> getUserByUsername(String email);

}
