package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.model.UserRequest;

@Component
public interface UserService {

	public HttpStatus create(List<UserRequest> userRequest) throws IllegalArgumentException;

	public List<User> getUsers();

	public User getUser(String id) throws IllegalArgumentException;

	public User getUserByEmail(String email);

	public HttpStatus deleteUser(String id);

	public HttpStatus update(User user) throws IllegalArgumentException;

}
