package com.atik.librarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "book")
public class Book {
	
	@Id
	private String id;
	private String name;
	private String author;
	private String publication;
	private int publicationYear;
	
}
