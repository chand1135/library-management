package com.atik.librarymanagement.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atik.librarymanagement.exception.RecordNotFoundException;
import com.atik.librarymanagement.model.Authentication;
import com.atik.librarymanagement.service.AuthenticationService;

@RestController
@RequestMapping(path = { "/api/v1" })
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody Authentication authentication) {

		if (Objects.nonNull(authentication.getEmail()))
			authentication.setEmail(authentication.getEmail().toLowerCase());

		return authenticationService.validateUser(authentication);
	}

	@RequestMapping(value = "/invalidate-token", method = RequestMethod.GET)
	public ResponseEntity<?> invalidateToken(@RequestHeader(name = "Authorization") String token) {

		String response = authenticationService.invalidateToken(token);

		if (Objects.nonNull(response)) {

			return ResponseEntity.status(HttpStatus.OK).body(response);

		} else {
			throw new RecordNotFoundException("Not added.");
		}
	}

}
