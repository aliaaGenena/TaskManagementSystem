package com.banquemisr.challeng05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.banquemisr.challeng05.error.BusinessException;
import com.banquemisr.challeng05.request.AuthRq;
import com.banquemisr.challeng05.response.AuthRs;
import com.banquemisr.challeng05.service.JwtService;
import com.banquemisr.challeng05.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtService jwtService;

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<AuthRs> login(@Valid @RequestBody AuthRq request) throws BusinessException {
		log.info("Received login request: {}", request);

		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserDetails user = userService.loadUserByUsername(request.getEmail());
		String jwt = jwtService.generateAccessToken(user);
		if (jwt == null || jwt.isEmpty()) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Token generation failed");
		}

		AuthRs response = new AuthRs();
		response.setAccessToken(jwt);
		response.setUserName(user.getUsername());

		return new ResponseEntity<AuthRs>(response, HttpStatus.OK);

	}

}
