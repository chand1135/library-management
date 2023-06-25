package com.atik.librarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "author")
public class Author {

	@Id
	private String id;
	private String name;
	private String dateOfBirth;
	private String dateOfDeath;

	public Author(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
