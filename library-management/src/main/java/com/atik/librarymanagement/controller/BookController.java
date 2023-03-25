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

import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.service.BookService;

@RestController
@RequestMapping(path = { "/api/v1/user" })
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Book book) {

		try {

			return ResponseEntity.status(service.create(book)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<?> getBooks() {

		return ResponseEntity.ok(service.getBooks());
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getBook(@PathVariable String name) {

		try {

			Book book = service.getBook(name);

			if (Objects.nonNull(book))
				return ResponseEntity.ok(book);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteBooks() {

		return ResponseEntity.status(service.deleteBooks()).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable String id) {

		return ResponseEntity.status(service.deleteBook(id)).build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Book book) {

		try {

			return ResponseEntity.status(service.update(book)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}