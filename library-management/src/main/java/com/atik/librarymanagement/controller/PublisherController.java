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

import com.atik.librarymanagement.model.Publisher;
import com.atik.librarymanagement.service.PublisherService;
import com.atik.librarymanagement.util.Util;

@RestController
@RequestMapping(path = { "/api/v1/publisher" })
public class PublisherController {

	@Autowired
	private PublisherService service;

	@Autowired
	private Util util;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody List<Publisher> publishers) {

		try {

			if (util.validatePublisher(publishers))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.create(publishers)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping
	public ResponseEntity<?> getPublishers() {

		return ResponseEntity.ok(service.getPublishers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPublisher(@PathVariable String id) {

		try {

			Publisher publisher = service.getPublisher(id);

			if (Objects.nonNull(publisher))
				return ResponseEntity.ok(publisher);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deletePublishers() {

		return ResponseEntity.status(service.deletePublishers()).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePublisher(@PathVariable String id) {

		return ResponseEntity.status(service.deletePublisher(id)).build();
	}

	@PutMapping
	public ResponseEntity<?> updatePublisher(@RequestBody Publisher publisher) {

		try {

			if (util.validatePublisher(publisher))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.status(service.update(publisher)).build();

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
