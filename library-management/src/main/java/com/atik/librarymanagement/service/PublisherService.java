package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Publisher;

@Component
public interface PublisherService {

	public HttpStatus create(List<Publisher> publishers) throws IllegalArgumentException;

	public List<Publisher> getPublishers();

	public Publisher getPublisher(String id) throws IllegalArgumentException;

	public HttpStatus deletePublishers();

	public HttpStatus deletePublisher(String id);

	public HttpStatus update(Publisher publisher) throws IllegalArgumentException;

}
