package com.atik.librarymanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	public Optional<Role> findByName(String roleName);
}
