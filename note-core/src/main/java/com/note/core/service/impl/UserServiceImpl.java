package com.note.core.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.note.core.dal.entity.UserEntity;
import com.note.core.dal.repository.UserRepository;
import com.note.core.service.UserService;
import com.note.core.service.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Qualifier("coreModelMapper")
	private ModelMapper modelMapper;

	@Override
	public Optional<User> getUser(Long userId) {
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		return userEntity.map(entity -> modelMapper.map(entity, User.class));
	}

}
