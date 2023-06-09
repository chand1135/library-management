package com.atik.librarymanagement.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.atik.librarymanagement.config.GlobalProperties;
import com.atik.librarymanagement.model.User;
import com.atik.librarymanagement.service.RoleService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	@Autowired
	private GlobalProperties globalProperties;

	@Autowired
	private RoleService roleService;

	public String extractId(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(globalProperties.getClientSecret()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Collection<? extends GrantedAuthority> extractPrivileges(String token) {

		return Arrays
				.asList(Jwts.parser().setSigningKey(globalProperties.getClientSecret()).parseClaimsJws(token).getBody()
						.get("AUTHORITIES_KEY").toString().replace("[", "").replace("]", "").split(", "))
				.stream().map(privilege -> new SimpleGrantedAuthority(privilege)).collect(Collectors.toList());
	}

	public String generateToken(User user) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("AUTHORITIES_KEY", roleService.getById(user.getRoleId()).getName());

		return createToken(claims, user.getId());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + globalProperties.getAccessTokenValidity()))
				.signWith(SignatureAlgorithm.HS512, globalProperties.getClientSecret()).compact();
	}

	public boolean validateToken(String token) {

		if (!isTokenExpired(token))
			return true;
		else
			return false;
	}
}