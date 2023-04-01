package com.atik.librarymanagement.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "book")
public class Book {

	@Id
	private String id;
	private String title;
	private String author;
	private int price;
	private String isbn;
	private int publicationYear;
	private String publisherId;
	private int numberOfPages;
	private List<String> genre;
	private List<Copy> copies;

}
