package com.atik.librarymanagement.util;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Book;
import com.atik.librarymanagement.model.User;

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

}
