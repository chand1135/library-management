package com.atik.librarymanagement.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.atik.librarymanagement.service.AuthenticationService;
import com.atik.librarymanagement.service.BlacklistedJwtTokenService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	// private final ObjectMapper mapper = new ObjectMapper();
	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class.getName());

	@Autowired
	private BlacklistedJwtTokenService blacklistedJwtTokenService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String userId = null;
		String token = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			token = authorizationHeader.substring(7);

			if (!token.equals("null")) {

				try {
					userId = jwtUtil.extractId(token);
				} catch (Exception e) {
					log.info("JWT Token Expired, Please login again");
				}
			}
		}

		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			if (jwtUtil.validateToken(token)) {

				if (!blacklistedJwtTokenService.getBlacklistedJwtTokenIsExistByTokenName(token)) {

					org.springframework.security.core.userdetails.User user = authenticationService
							.loadUserById(userId);

					if (Objects.nonNull(user)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userId, null, user.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}

			} else {

				Map<String, Object> errorDetails = new HashMap<>();
				errorDetails.put("message", "Token expire");
				log.info("JWT Token Expired" + errorDetails);
			}
		}
		chain.doFilter(request, response);
	}
}
