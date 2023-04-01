package com.atik.librarymanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

}
