package com.rabit.herkoakos.bootjpacrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabit.herkoakos.bootjpacrud.exceptions.ResourceNotFoundException;
import com.rabit.herkoakos.bootjpacrud.models.Advertisement;
import com.rabit.herkoakos.bootjpacrud.models.User;
import com.rabit.herkoakos.bootjpacrud.repositories.AdvertisementRepository;
import com.rabit.herkoakos.bootjpacrud.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdvertisementRepository advertisementRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found at this id" + userId));
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/users/{userid}/advertisements")
	public Page<Advertisement> getAllAdvertisementsByUserId(@PathVariable (value = "userid") Long userId, Pageable pageable){
		return advertisementRepository.findByUserId(userId, pageable);
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable (value = "id") Long userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found at this id " + userId));
		
		user.setName(userDetails.getName());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable (value="id") Long userId) throws ResourceNotFoundException{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found at this id " + userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
		
	}
}
