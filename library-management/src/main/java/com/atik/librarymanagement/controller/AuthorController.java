package com.atik.librarymanagement.controller;

import java.util.List;
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

import com.atik.librarymanagement.model.Author;
import com.atik.librarymanagement.service.AuthorService;
import com.atik.librarymanagement.util.Util;

@RestController
@RequestMapping(path = { "/api/v1/author" })
public class AuthorController {

	@Autowired
	private AuthorService service;

	@Autowired
	private Util util;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody List<Author> authors) {

		try {

			if (util.validateAuthor(authors))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.create(authors)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<?> getAuthors() {

		return ResponseEntity.ok(service.getAuthors());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAuthor(@PathVariable String id) {

		try {

			if (Objects.isNull(id))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			Author author = service.getAuthor(id);

			if (Objects.nonNull(author))
				return ResponseEntity.ok(author);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable String id) {

		if (Objects.isNull(id))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.deleteAuthor(id)).build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Author author) {

		try {

			if (util.validateAuthor(author))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.update(author)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
