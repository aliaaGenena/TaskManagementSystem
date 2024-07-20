package com.banquemisr.challeng05.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.banquemisr.challeng05.dao.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {

	@Autowired
	UserRepo userRepo;

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	public String generateAccessToken(UserDetails user) {
		String token = Jwts.builder().setSubject(user.getUsername()).setIssuer("banquemisr")
				.claim("roles", user.getAuthorities()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

		log.info("Generated Token: {}", token);

		return token;

	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			log.error("JWT expired", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			log.error("Token is null, empty or only whitespace", ex.getMessage());
		} catch (MalformedJwtException ex) {
			log.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			log.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			log.error("Signature validation failed");
		}
		return false;
	}

	public Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public UserDetails getUserDetails(String token) {

		Claims claims = parseClaims(token);

		String jwtSubject = claims.getSubject();

		// Extract roles from claims
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		List<Map<String, ?>> roles = (List<Map<String, ?>>) claims.get("roles");

		if (roles != null) {
			for (Map<String, ?> role : roles) {
				// Extract authority from each role object
				String authority = (String) role.get("authority");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + authority));
			}
		}

		return new User(jwtSubject, "", authorities);
	}

}
