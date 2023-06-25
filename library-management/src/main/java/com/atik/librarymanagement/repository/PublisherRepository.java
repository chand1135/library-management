package com.atik.librarymanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.Publisher;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {

	public Optional<Publisher> findByName(String name);

}
