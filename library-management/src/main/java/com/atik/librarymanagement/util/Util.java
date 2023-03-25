package com.atik.librarymanagement.util;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Book;

@Component
public class Util {

	public boolean validateBook(Book book) {

		if (Objects.isNull(book))
			return true;

		if (Objects.isNull(book.getName()))
			return true;

		if (Objects.isNull(book.getAuthor()))
			return true;

		if (Objects.isNull(book.getPublication()))
			return true;

		if (Objects.isNull(book.getPublicationYear()))
			return true;

		return false;
	}

}
