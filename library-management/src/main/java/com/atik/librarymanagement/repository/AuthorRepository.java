package com.atik.librarymanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

}
