package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Role;

@Component
public interface RoleService {

	public HttpStatus create(Role role);

	public Role getById(String id);

	public Role getByName(String name);

	public List<Role> getRoles();

	public HttpStatus deleteById(String id);

	public HttpStatus deleteRoles();

	public HttpStatus update(Role role);
}
