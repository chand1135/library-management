package com.atik.librarymanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Genre;

@Component
public interface GenreService {

	public HttpStatus create(List<Genre> genres) throws IllegalArgumentException;

	public List<Genre> getGenres();

	public Genre getGenre(String id) throws IllegalArgumentException;

	public HttpStatus deleteGenre(String id);

	public HttpStatus update(Genre genre) throws IllegalArgumentException;

}
