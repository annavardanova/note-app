package com.note.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users", produces = "application/hal+json")
public class UserController {
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		return null;
	};

}
