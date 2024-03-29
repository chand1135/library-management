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

import com.atik.librarymanagement.model.Genre;
import com.atik.librarymanagement.service.GenreService;
import com.atik.librarymanagement.util.Validation;

@RestController
@RequestMapping(path = { "/api/v1/genre" })
public class GenreController {

	@Autowired
	private GenreService service;

	@Autowired
	private Validation util;

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> create(@RequestBody List<Genre> genres) {

		try {

			if (util.validateGenre(genres))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.create(genres)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getGenres() {

		return ResponseEntity.ok(service.getGenres());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getGenre(@PathVariable String id) {

		try {

			if (Objects.isNull(id))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			Genre genre = service.getGenre(id);

			if (Objects.nonNull(genre))
				return ResponseEntity.ok(genre);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteGenre(@PathVariable String id) {

		if (Objects.isNull(id))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.deleteGenre(id)).build();
	}

	@PutMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('modifier')")
	public ResponseEntity<?> updateGenre(@RequestBody Genre genre) {

		try {

			if (util.validateGenre(genre))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.update(genre)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
