package com.atik.librarymanagement.service.impl;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.model.BlacklistedJwtToken;
import com.atik.librarymanagement.repository.BlacklistedJwtTokenRepository;
import com.atik.librarymanagement.service.BlacklistedJwtTokenService;
import com.atik.librarymanagement.util.Constant;

/**
 * @author Mohammad Enayatullah
 *
 */
@Service
public class BlacklistedJwtTokenServiceImpl implements BlacklistedJwtTokenService {

	@Autowired
	private BlacklistedJwtTokenRepository repository;

	@Override
	public String createBlacklistedJwtToken(BlacklistedJwtToken blacklistedJwtToken) {

		try {

			repository.save(blacklistedJwtToken);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(Constant.MESSAGE, Constant.CREATED);

			return jsonObject.toString();

		} catch (Exception e) {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put(Constant.MESSAGE, Constant.NOT_CREATED);
			return jsonObject.toString();
		}
	}

	@Override
	public BlacklistedJwtToken getBlacklistedJwtTokenById(String id) {

		return repository.findById(id).get();
	}

	@Override
	public BlacklistedJwtToken getBlacklistedJwtTokenByTokenName(String token) {

		Optional<BlacklistedJwtToken> optional = repository.findByToken(token);

		if (optional.isPresent())
			return optional.get();

		return null;
	}

	@Override
	public boolean getBlacklistedJwtTokenIsExistByTokenName(String token) {

		return repository.existsByToken(token);
	}

	@Override
	public String deleteBlacklistedJwtTokenById(String id) {

		repository.deleteById(id);

		return Constant.DELETED;
	}

	@Override
	public String deleteBlacklistedJwtToken() {

		repository.deleteAll();

		return Constant.DELETED;
	}

}
