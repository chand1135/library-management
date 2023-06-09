package com.atik.librarymanagement.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.model.UserRequest;
import com.atik.librarymanagement.service.UserService;
import com.atik.librarymanagement.util.Validation;

@RestController
@RequestMapping(path = { "/api/v1/user" })
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private Validation util;

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> create(@RequestBody List<UserRequest> userRequest) {

		try {

			if (util.validateUser(userRequest))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.create(userRequest)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getUsers() {

		return ResponseEntity.ok(service.getUsers());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getUser(@PathVariable String id) {

		try {

			User user = service.getUser(id);

			if (Objects.nonNull(user))
				return ResponseEntity.ok(user);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {

		return ResponseEntity.status(service.deleteUser(id)).build();
	}

	@PutMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('modifier')")
	public ResponseEntity<?> update(@RequestBody User user) {

		try {

			if (util.validateUser(user))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.update(user)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
