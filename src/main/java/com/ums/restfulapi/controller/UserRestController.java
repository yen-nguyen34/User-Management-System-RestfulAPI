package com.ums.restfulapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.restfulapi.entity.User;
import com.ums.restfulapi.exception.ResourceNotFoundException;
import com.ums.restfulapi.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	// get all users
	@GetMapping
	public List<User> getUser() {
		return this.userRepository.findAll();
	}

	// get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	}

	// create user
	@PostMapping
	public User addUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	// update user by id
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable (value = "id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}

	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable (value = "id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}

}
