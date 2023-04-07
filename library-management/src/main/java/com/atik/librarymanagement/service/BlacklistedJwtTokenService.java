package com.atik.librarymanagement.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atik.librarymanagement.model.BlacklistedJwtToken;

/**
 * @author Mohammad Enayatullah
 *
 */
@Component
@Qualifier("BlacklistedJwtTokenService")
public interface BlacklistedJwtTokenService {

	public String createBlacklistedJwtToken(BlacklistedJwtToken blacklistedJwtToken);

	public BlacklistedJwtToken getBlacklistedJwtTokenById(String id);

	public BlacklistedJwtToken getBlacklistedJwtTokenByTokenName(String token);

	public boolean getBlacklistedJwtTokenIsExistByTokenName(String token);

	public String deleteBlacklistedJwtTokenById(String id);

	public String deleteBlacklistedJwtToken();
}
