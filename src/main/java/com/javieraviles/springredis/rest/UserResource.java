package com.javieraviles.springredis.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javieraviles.springredis.entities.User;
import com.javieraviles.springredis.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<User>> getUsers() {
    	final List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<User> getUsers(@PathVariable("id") final String userId) {
    	final User user = userService.findById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> createUser(@RequestBody final User user) {
    	userService.save(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> updateUser(@PathVariable("id") final String userId, @RequestBody final User user) {
    	// TODO validate userId exists in DB
    	userService.save(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final String userId) {
    	userService.delete(userId);
    	return new ResponseEntity<>(HttpStatus.OK);
	}

}
