package com.note.core;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteCoreApplication.class, args);
	}
	
	@Bean
	public ModelMapper coreModelMapper() {
	    return new ModelMapper();
	}
}
