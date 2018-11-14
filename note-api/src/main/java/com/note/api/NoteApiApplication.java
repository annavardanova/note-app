package com.note.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.note.api.security.ApiPermissionEvaluator;

/**
 * 
 * Standard spring boot configuration. 
 * Further development of the project may suggest external profile specific properties or configuration management server.
 * @author annavardanova
 *
 */
@ComponentScan(basePackages = "com.note")
@SpringBootApplication
public class NoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper apiModelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public ApiPermissionEvaluator permissionEvaluator(){
		ApiPermissionEvaluator permissionEvaluator = new ApiPermissionEvaluator();
		return permissionEvaluator;
	}
}
