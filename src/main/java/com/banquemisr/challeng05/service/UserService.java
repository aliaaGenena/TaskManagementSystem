package com.banquemisr.challeng05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.banquemisr.challeng05.dao.UserRepo;
import com.banquemisr.challeng05.pojo.User;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

		if (user.getRoles().isEmpty()) {
			log.info("User has no roles assigned");
		}

		return user;

	}

}
