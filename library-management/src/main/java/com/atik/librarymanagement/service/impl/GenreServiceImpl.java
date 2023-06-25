package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.Genre;
import com.atik.librarymanagement.repository.GenreRepository;
import com.atik.librarymanagement.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository repository;

	@Override
	public HttpStatus create(List<Genre> genres) throws IllegalArgumentException {

		try {

			genres.stream().forEach(genre -> {

				if (Objects.isNull(genre.getId()))
					genre.setId(UUID.randomUUID().toString());

				repository.save(genre);
			});

			return HttpStatus.CREATED;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Genre> getGenres() {

		return repository.findAll();

	}

	@Override
	public Genre getGenre(String id) throws IllegalArgumentException {

		try {

			Optional<Genre> optional = repository.findById(id);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public Genre getGenreByName(String name) throws IllegalArgumentException {

		try {

			Optional<Genre> optional = repository.findByName(name);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	@Override
	public HttpStatus deleteGenre(String id) {

		repository.deleteById(id);

		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus update(Genre genre) throws IllegalArgumentException {

		try {

			Genre updateGenre = getGenre(genre.getId());

			if (Objects.nonNull(updateGenre)) {

				if (Objects.nonNull(genre.getName()))
					updateGenre.setName(genre.getName());
			}

			repository.save(updateGenre);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

}
