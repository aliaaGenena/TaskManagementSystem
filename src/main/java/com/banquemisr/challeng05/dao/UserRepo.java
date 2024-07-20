package com.banquemisr.challeng05.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.banquemisr.challeng05.pojo.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username) throws UsernameNotFoundException;

	public Optional<User> findByEmail(String email);

}
