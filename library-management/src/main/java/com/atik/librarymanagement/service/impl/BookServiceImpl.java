package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.repository.BookRepository;
import com.atik.librarymanagement.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository repository;

	@Override
	public HttpStatus create(Book book) throws IllegalArgumentException {

		try {
			
			book.setId(UUID.randomUUID().toString());
			
			repository.save(book);
			
			return HttpStatus.CREATED;
		} catch (IllegalArgumentException e) {
			
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Book> getBooks() {

		return repository.findAll();
	}

	@Override
	public Book getBook(String name) throws IllegalArgumentException {

		try {

			Optional<Book> optional = repository.findByName(name);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public HttpStatus deleteBooks() {

		repository.deleteAll();
		
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus deleteBook(String id) {

		repository.deleteById(id);
		
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus update(Book book) throws IllegalArgumentException {

		try {

			Book updateBook = getBook(book.getId());

			if (Objects.nonNull(updateBook)) {

				if (Objects.nonNull(book.getPublicationYear()))
					updateBook.setPublicationYear(book.getPublicationYear());

			}

			repository.save(updateBook);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

}
