package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Book;

@Component
public interface BookService {

	public HttpStatus create(Book book) throws IllegalArgumentException;

	public List<Book> getBooks();

	public Book getBook(String id) throws IllegalArgumentException;

	public HttpStatus deleteBook(String id);

	public HttpStatus update(Book book) throws IllegalArgumentException;

}
