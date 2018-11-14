package com.note.api.security;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.note.core.service.NoteService;
import com.note.core.service.UserService;
import com.note.core.service.exception.NoteNotFoundException;
import com.note.core.service.model.Note;
import com.note.core.service.model.User;


public class ApiPermissionEvaluator implements PermissionEvaluator {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private  UserService userService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return false;
	}
	
	/**
	 * Trivial implementation of the permissions evaluation. Potentially should be updated 
	 * with extensible permission evaluation logic and custom user details.
	 */

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		String permissionStr = (String)permission;
		org.springframework.security.core.userdetails.User apiUser 
		 = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
		Optional<User> user = userService.getUserByUsername(apiUser.getUsername());
		
		//should be cleaned up a little: remove constant target type strings, maybe clean up a little around getNote 
		if(!user.isPresent() || !targetType.equals("note")) {
			return false;
		}
		
		//in case when operating on existing note check the ownership. Otherwise let proceed.
		if("READ".equals(permissionStr) || "EDIT".equals(permissionStr) || "DELETE".equals(permissionStr)) {
			//this part could be optimized wit
			Note note = noteService.getNote((Long)targetId).orElseThrow(NoteNotFoundException :: new);
			return note.getUserId().equals(user.get().getId());
		}else {
			return true;
		}
	}

}
