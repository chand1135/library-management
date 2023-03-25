package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.repository.UserRepository;
import com.atik.librarymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	public HttpStatus create(User user) throws IllegalArgumentException {

		try {

//			if (repository.existsById(user.getId()))
//				return HttpStatus.CONFLICT;

			user.setId(UUID.randomUUID().toString());

			repository.save(user);

			return HttpStatus.CREATED;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	public List<User> getUsers() {

		return repository.findAll();
	}

	public User getUser(String id) throws IllegalArgumentException {

		try {

			Optional<User> optional = repository.findById(id);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	public HttpStatus deleteUsers() {

		repository.deleteAll();

		return HttpStatus.NO_CONTENT;
	}

	public HttpStatus deleteUser(String id) {

		repository.deleteById(id);

		return HttpStatus.NO_CONTENT;
	}

	public HttpStatus update(User user) throws IllegalArgumentException {

		try {

			User updateUser = getUser(user.getId());

			if (Objects.nonNull(updateUser)) {

				if (Objects.nonNull(user.getName()))
					updateUser.setName(user.getName());

				if (Objects.nonNull(user.getPhoneNumber()))
					updateUser.setPhoneNumber(user.getPhoneNumber());

				if (Objects.nonNull(user.getAddress()))
					updateUser.setAddress(user.getAddress());

			}

			repository.save(updateUser);

			return HttpStatus.NO_CONTENT;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}

	}

}
