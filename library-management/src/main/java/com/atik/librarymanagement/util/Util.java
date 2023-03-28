package com.atik.librarymanagement.util;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Author;
import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.model.Genre;
import com.atik.librarymanagement.model.Publisher;
import com.atik.librarymanagement.model.User;

@Component
public class Util {

	public boolean validateBook(Book book) {

		if (Objects.isNull(book))
			return true;

		if (Objects.isNull(book.getTitle()))
			return true;

		if (Objects.isNull(book.getAuthor()))
			return true;

		if (Objects.isNull(book.getPrice()))
			return true;

		if (Objects.isNull(book.getIsbn()))
			return true;

		if (Objects.isNull(book.getPublicationYear()))
			return true;

		if (Objects.isNull(book.getPublisherId()))
			return true;

		if (Objects.isNull(book.getNumberOfPages()))
			return true;

		if (Objects.isNull(book.getGenre()))
			return true;

		if (Objects.isNull(book.getCopies()))
			return true;

		return false;
	}

	public boolean validateUser(User user) {

		if (Objects.isNull(user))
			return true;

		if (Objects.isNull(user.getName()))
			return true;

		if (Objects.isNull(user.getEmail()))
			return true;

		if (Objects.isNull(user.getPhoneNumber()))
			return true;

		if (Objects.isNull(user.getAddress()))
			return true;

		return false;
	}

	public boolean validatePublisher(List<Publisher> publishers) {

		for (var i = 0; i < publishers.size(); i++) {

			Publisher publisher = publishers.get(i);

			if (Objects.isNull(publisher))
				return true;

			if (Objects.isNull(publisher.getName()))
				return true;

			if (Objects.isNull(publisher.getAddress()))
				return true;
		}

		return false;
	}

	public boolean validatePublisher(Publisher publisher) {

		if (Objects.isNull(publisher))
			return true;

		if (Objects.isNull(publisher.getName()))
			return true;

		if (Objects.isNull(publisher.getAddress()))
			return true;

		return false;
	}

	public boolean validateAuthor(Author author) {

		if (Objects.isNull(author))
			return true;

		if (Objects.isNull(author.getName()))
			return true;

		if (Objects.isNull(author.getDateOfBirth()))
			return true;

		if (Objects.isNull(author.getDateOfDeath()))
			return true;

		return false;
	}

	public boolean validateGenre(Genre genre) {

		if (Objects.isNull(genre))
			return true;

		if (Objects.isNull(genre.getName()))
			return true;

		return false;
	}

}
