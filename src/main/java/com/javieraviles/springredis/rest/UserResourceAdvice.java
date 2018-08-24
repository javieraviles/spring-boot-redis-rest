package com.javieraviles.springredis.rest;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = {UserResource.class})
@RequestMapping(produces = MediaType.APPLICATION_JSON)
public class UserResourceAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> assertionException(final NotFoundException e) {
		return new ResponseEntity<>(errorToJson(e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RedisConnectionFailureException.class)
	public ResponseEntity<String> assertionException(final RedisConnectionFailureException e) {
		return new ResponseEntity<>(errorToJson("Redis not available"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> assertionException(final IllegalArgumentException e) {
		return new ResponseEntity<>(errorToJson(e.getMessage()),HttpStatus.BAD_REQUEST);
	}

	private String errorToJson(final String error) {
		return "{\"error\":\"" + error + "\"}";
	}

}
