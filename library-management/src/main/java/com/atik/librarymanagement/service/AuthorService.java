package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Author;

@Component
public interface AuthorService {

	public HttpStatus create(Author author) throws IllegalArgumentException;

	public List<Author> getAuthors();

	public Author getAuthor(String id) throws IllegalArgumentException;

	public HttpStatus deleteAuthor(String id);

	public HttpStatus update(Author author) throws IllegalArgumentException;

}
