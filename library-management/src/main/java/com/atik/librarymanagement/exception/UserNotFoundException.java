package com.atik.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String exception) {

		super(exception);
	}
}