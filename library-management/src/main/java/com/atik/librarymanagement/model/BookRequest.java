package com.atik.librarymanagement.model;

import java.time.Year;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BookRequest {

	@Id
	private String id;
	private String title;
	private String authorName;
	private String isbn;
	private Double price;
	private Year publisherYear;
	private String publisherName;
	private Integer numberOfPages;
	private List<String> genreNames;
	private List<Copy> copies;

}
