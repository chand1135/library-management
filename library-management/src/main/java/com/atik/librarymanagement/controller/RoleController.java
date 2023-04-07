package com.atik.librarymanagement.controller;

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

import com.atik.librarymanagement.model.Role;
import com.atik.librarymanagement.service.RoleService;

@RestController
@RequestMapping(path = { "/api/v1/role" })
public class RoleController {

	@Autowired
	private RoleService service;

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> create(@RequestBody Role role) {

		if (Objects.isNull(role))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		if (Objects.isNull(role.getName()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.create(role)).build();

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getById(@PathVariable String id) {

		if (Objects.isNull(id))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		var role = service.getById(id);

		if (Objects.nonNull(role))

			return ResponseEntity.ok(role);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@GetMapping("/name/{name}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> getByName(@PathVariable String name) {

		if (Objects.isNull(name))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		var role = service.getByName(name);

		if (Objects.nonNull(role))

			return ResponseEntity.ok(role);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('user') or hasAuthority('modifier')")
	public ResponseEntity<?> gets() {

		var userList = service.getRoles();

		if (Objects.nonNull(userList)) {

			return ResponseEntity.status(HttpStatus.OK).body(userList);

		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteById(@PathVariable String id) {

		if (Objects.isNull(id))
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.deleteById(id)).build();

	}

	@DeleteMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteAll() {

		return ResponseEntity.status(service.deleteRoles()).build();
	}

	@PutMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('modifier')")
	public ResponseEntity<?> update(@RequestBody Role role) {

		if (Objects.isNull(role))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		if (Objects.isNull(role.getId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		if (Objects.isNull(role.getName()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.status(service.update(role)).build();

	}

}
