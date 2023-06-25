package com.atik.librarymanagement.model;

import java.time.Year;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "book")
public class Book {

	@Id
	private String id;
	private String title;
	private String authorId;
	private String isbn;
	private Double price;
	private Year publicationYear;
	private String publisherId;
	private Integer numberOfPages;
	private List<String> genreIds;
	private List<Copy> copies;
	private Integer copiesCount;
}
