package com.atik.librarymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.Author;
import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.model.BookRequest;
import com.atik.librarymanagement.model.Copy;
import com.atik.librarymanagement.model.Genre;
import com.atik.librarymanagement.model.Publisher;
import com.atik.librarymanagement.repository.BookRepository;
import com.atik.librarymanagement.service.AuthorService;
import com.atik.librarymanagement.service.BookService;
import com.atik.librarymanagement.service.GenreService;
import com.atik.librarymanagement.service.PublisherService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private GenreService genreService;

	@Override
	public HttpStatus create(List<BookRequest> bookRequests) throws IllegalArgumentException {

		try {

			for (BookRequest bookRequest : bookRequests) {

				Book book;

				Optional<Book> bookOption = repository.findByIsbn(bookRequest.getIsbn());

				// If a book is present in database with ISBN number then we're increasing a
				// copy only
				if (Objects.nonNull(bookOption) && bookOption.isPresent() && Objects.nonNull(bookOption.get())) {

					book = bookOption.get();

					List<Copy> bookCopies = book.getCopies();

					bookRequest.getCopies().forEach(copy -> {

						bookCopies.add(Copy.builder().barcode(copy.getBarcode()).status(copy.getStatus()).build());
					});

					book.setCopies(bookCopies);

					book.setCopiesCount(bookCopies.size());

				}

				// If book is not present in database then create a new object
				else {

					book = Book.builder().id(UUID.randomUUID().toString()).title(bookRequest.getTitle())
							.authorName(bookRequest.getAuthorName()).isbn(bookRequest.getIsbn())
							.price(bookRequest.getPrice()).publicationYear(bookRequest.getPublisherYear())
							.publisherName(bookRequest.getPublisherName()).numberOfPages(bookRequest.getNumberOfPages())
							.genreNames(bookRequest.getGenreNames()).copies(bookRequest.getCopies())
							.copiesCount(bookRequest.getCopies().size()).build();

					Author author = authorService.getAuthorByName(bookRequest.getAuthorName());

					if (Objects.nonNull(author))

						book.setAuthorId(author.getId());

					else {

						String authorId = UUID.randomUUID().toString();

						HttpStatus status = authorService
								.create(List.of(new Author(authorId, bookRequest.getAuthorName())));

						if (status.equals(HttpStatus.CREATED))
							book.setAuthorId(authorId);
					}

					Publisher publisher = publisherService.getPublisherByName(bookRequest.getPublisherName());

					if (Objects.nonNull(publisher))

						book.setPublisherId(publisher.getId());

					else {

						String publisherId = UUID.randomUUID().toString();

						HttpStatus status = publisherService
								.create(List.of(new Publisher(publisherId, bookRequest.getPublisherName())));

						if (status.equals(HttpStatus.CREATED))
							book.setPublisherId(publisherId);
					}

					List<String> genreIds = new ArrayList<>();
					List<Genre> genres = new ArrayList<>();

					bookRequest.getGenreNames().forEach(genreName -> {

						Genre genre = genreService.getGenreByName(genreName);

						if (Objects.nonNull(genre))

							genreIds.add(genre.getId());

						else {

							String genreId = UUID.randomUUID().toString();

							genres.add(new Genre(genreId, genreName));

							genreIds.add(genreId);
						}

					});

					if (Objects.nonNull(genres))
						genreService.create(genres);

					book.setGenreIds(genreIds);

				}

				repository.save(book);
			}

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
	public Book getBook(String id) throws IllegalArgumentException {

		try {

			Optional<Book> optional = repository.findById(id);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
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

			}

			repository.save(updateBook);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

}
