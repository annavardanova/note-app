package com.note.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.note")
@SpringBootApplication
public class NoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteApiApplication.class, args);
	}
}
