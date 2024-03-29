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

import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.model.BookRequest;
import com.atik.librarymanagement.service.BookService;
import com.atik.librarymanagement.util.Validation;

@RestController
@RequestMapping(path = { "/api/v1/book" })
public class BookController {

	@Autowired
	private BookService service;

	@Autowired
	private Validation util;

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> create(@RequestBody List<BookRequest> books) {

		try {

			if (util.validateBook(books))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.create(books)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getBooks() {

		return ResponseEntity.ok(service.getBooks());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getBook(@PathVariable String id) {

		try {

			if (Objects.isNull(id))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			Book book = service.getBook(id);

			if (Objects.nonNull(book))
				return ResponseEntity.ok(book);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteBook(@PathVariable String id) {

		if (Objects.isNull(id))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.deleteBook(id)).build();
	}

	@PutMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('modifier')")
	public ResponseEntity<?> update(@RequestBody Book book) {

		try {

			if (util.validateBook(book))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.update(book)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
