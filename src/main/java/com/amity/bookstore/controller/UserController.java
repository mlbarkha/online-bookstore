package com.amity.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amity.bookstore.entity.User;
import com.amity.bookstore.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/login")
	public User loginUser(@RequestParam String username, @RequestParam String password) {
		return userService.loginUser(username, password);
	}
}
