package com.atik.librarymanagement.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UserRequest {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private long phoneNumber;
	private String address;
	private String roleName;

}
