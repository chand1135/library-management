package com.atik.librarymanagement.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.service.UserService;

@RestController
@RequestMapping(path = { "/api/v1/user" })
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {

		try {

			return ResponseEntity.status(service.create(user)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<?> getUsers() {

		return ResponseEntity.ok(service.getUsers());
	}

	@GetMapping("/{id}")
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

	@DeleteMapping
	public ResponseEntity<?> deleteUsers() {

		return ResponseEntity.status(service.deleteUsers()).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {

		return ResponseEntity.status(service.deleteUser(id)).build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user) {

		try {

			return ResponseEntity.status(service.update(user)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
