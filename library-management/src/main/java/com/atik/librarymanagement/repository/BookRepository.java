package com.atik.librarymanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findByName(String name);

}
