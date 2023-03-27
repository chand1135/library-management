package com.atik.librarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "genre")
public class Genre {
	
	@Id
	private String id;
	private String name;

}
