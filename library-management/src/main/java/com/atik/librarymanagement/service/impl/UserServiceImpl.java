package com.atik.librarymanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.exception.RecordNotFoundException;
import com.atik.librarymanagement.model.Role;
import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.model.UserRequest;
import com.atik.librarymanagement.repository.UserRepository;
import com.atik.librarymanagement.service.RoleService;
import com.atik.librarymanagement.service.UserService;
import com.atik.librarymanagement.util.Constant;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Override
	public HttpStatus create(List<UserRequest> userRequests) throws IllegalArgumentException {

		try {

			userRequests.stream().forEach(userRequest -> {

				if (Objects.nonNull(userRequest.getEmail())) {

					Optional<User> optional = repository.findByEmail(userRequest.getEmail());

					if (!optional.isPresent()) {

						Role role = roleService.getByName(userRequest.getRoleName().trim());

						if (Objects.nonNull(role) && Objects.nonNull(role.getId()))

							repository.save(User.builder().id(UUID.randomUUID().toString()).name(userRequest.getName())
									.email(userRequest.getEmail())
									.password(passwordEncoder.encode(userRequest.getPassword().trim()))
									.phoneNumber(userRequest.getPhoneNumber()).address(userRequest.getAddress())
									.roleId(role.getId()).build());

						else
							throw new RecordNotFoundException(Constant.ROLE_NOT_FOUND);

					} else
						throw new RecordNotFoundException(Constant.EMAIL_ALREADY_EXISTS);

				} else
					throw new RecordNotFoundException(Constant.INVALID_EMAIL);
			});

			return HttpStatus.CREATED;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
	}

	public List<User> getUsers() {

		var users = repository.findAll();

		users.stream().forEach(user -> {

			user.setPassword(null);
		});

		return users;
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

	public User getUserByEmail(String email) {

		try {

			Optional<User> optional = repository.findByEmail(email);

			if (optional.isPresent())
				return optional.get();

			return null;

		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException();
		}
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
