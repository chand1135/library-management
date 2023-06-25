package com.atik.librarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private long phoneNumber;
	private String address;
	private String roleId;

}
