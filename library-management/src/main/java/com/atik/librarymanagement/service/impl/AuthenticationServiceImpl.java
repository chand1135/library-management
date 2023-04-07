package com.atik.librarymanagement.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.exception.RecordNotFoundException;
import com.atik.librarymanagement.exception.UserNotFoundException;
import com.atik.librarymanagement.model.Authentication;
import com.atik.librarymanagement.model.BlacklistedJwtToken;
import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.security.JwtUtil;
import com.atik.librarymanagement.service.AuthenticationService;
import com.atik.librarymanagement.service.BlacklistedJwtTokenService;
import com.atik.librarymanagement.service.RoleService;
import com.atik.librarymanagement.service.UserService;
import com.atik.librarymanagement.util.Constant;
import com.atik.librarymanagement.util.JsonResponseUtil;
import com.mongodb.client.MongoDatabase;

/**
 * @author Mohammad Enayatullah
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BlacklistedJwtTokenService blacklistedJwtTokenService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<?> validateUser(Authentication authentication) {

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authentication.getEmail(), authentication.getPassword()));

		} catch (UserNotFoundException rn) {

			throw new UserNotFoundException(Constant.RECORD_NOT_FOUND);
		}

		final User user = userService.getUserByEmail(authentication.getEmail());

		final String jwt = jwtTokenUtil.generateToken(user);

		return ResponseEntity.ok(JsonResponseUtil.getResponse(jwt, user.getName()));
	}

	public String ping() {

		try {

			MongoDatabase answer = mongoTemplate.getDb();

			return answer.getName();

		} catch (Exception e) {

			return e.getMessage();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws RecordNotFoundException {

		if (ping().equals(null)) {

			LOGGER.info(Constant.DATABASE_NOT_CONNECTED);
			throw new RecordNotFoundException(Constant.DATABASE_NOT_CONNECTED);
		}

		User user = userService.getUserByEmail(email);

		if (Objects.nonNull(user)) {

			Set<GrantedAuthority> authorities = new HashSet<>();

			authorities.add(new SimpleGrantedAuthority(roleService.getById(user.getRole()).getName()));

			return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),
					authorities);
		} else
			throw new RecordNotFoundException(Constant.USER_NOT_FOUND);
	}

	public org.springframework.security.core.userdetails.User loadUserById(String id) {
		try {

			User user = userService.getUser(id);

			if (Objects.nonNull(user)) {

				Set<GrantedAuthority> authorities = new HashSet<>();

				authorities.add(new SimpleGrantedAuthority(roleService.getById(user.getRole()).getName()));

				return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), authorities);
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String invalidateToken(String token) {

		BlacklistedJwtToken blacklistedJwtToken = new BlacklistedJwtToken();

		blacklistedJwtToken.setId(UUID.randomUUID().toString());
		blacklistedJwtToken.setToken(token.substring(7));

		return blacklistedJwtTokenService.createBlacklistedJwtToken(blacklistedJwtToken);
	}

}
