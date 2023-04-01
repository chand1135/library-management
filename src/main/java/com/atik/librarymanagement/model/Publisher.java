package com.atik.librarymanagement.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "publisher")
public class Publisher {

	private String id;
	private String name;
	private String address;

}
