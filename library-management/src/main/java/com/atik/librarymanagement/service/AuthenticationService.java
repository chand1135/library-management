package com.atik.librarymanagement.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.Authentication;

/**
 * @author Mohammad Enayatullah
 *
 */
@Component
@Qualifier("AuthenticationService")
public interface AuthenticationService {

	public ResponseEntity<?> validateUser(Authentication authentication);

	public String invalidateToken(String token);
	
	public User loadUserById(String userId);
}
