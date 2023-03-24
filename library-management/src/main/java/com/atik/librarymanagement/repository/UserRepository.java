package com.atik.librarymanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
