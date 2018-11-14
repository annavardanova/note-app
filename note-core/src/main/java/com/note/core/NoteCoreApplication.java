package com.note.core;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The core module encapsulates both dal and business layers. 
 * This is done for the sake of simplicity and maintainability of single version between packaged layers. 
 * In general it is highly probable that each of the layers would be located in a separate project. 
 * This would emphasize the layers and enforce the direction between them.
 * @author annavardanova
 *
 */

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
