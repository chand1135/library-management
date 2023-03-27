package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.Author;
import com.atik.librarymanagement.repository.AuthorRepository;
import com.atik.librarymanagement.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository repository;

	@Override
	public HttpStatus create(Author author) throws IllegalArgumentException {

		try {

			author.setId(UUID.randomUUID().toString());

			repository.save(author);

			return HttpStatus.CREATED;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Author> getAuthors() {

		return repository.findAll();
	}

	@Override
	public Author getAuthor(String id) throws IllegalArgumentException {

		try {

			Optional<Author> optional = repository.findById(id);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public HttpStatus deleteAuthor(String id) {

		repository.deleteById(id);

		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus update(Author author) throws IllegalArgumentException {

		try {

			Author updateAuthor = getAuthor(author.getId());

			if (Objects.nonNull(updateAuthor)) {

				if (Objects.nonNull(author.getName()))
					updateAuthor.setName(author.getName());

				if (Objects.nonNull(author.getDateOfBirth()))
					updateAuthor.setDateOfBirth(author.getDateOfBirth());

				if (Objects.nonNull(author.getDateOfDeath()))
					updateAuthor.setDateOfDeath(author.getDateOfDeath());

			}

			repository.save(updateAuthor);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

}
