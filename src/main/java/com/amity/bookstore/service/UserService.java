package com.amity.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amity.bookstore.entity.User;
import com.amity.bookstore.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; 

	public User registerUser(User user) {
		Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser.isPresent()) {
			throw new RuntimeException("Username already exists");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles(user.getRole()).build();
	}
}
