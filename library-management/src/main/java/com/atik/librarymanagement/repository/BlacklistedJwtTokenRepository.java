package com.atik.librarymanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atik.librarymanagement.model.BlacklistedJwtToken;

@Repository
public interface BlacklistedJwtTokenRepository extends MongoRepository<BlacklistedJwtToken, String> {

	public Optional<BlacklistedJwtToken> findByToken(String token);

	public boolean existsByToken(String token);
}
