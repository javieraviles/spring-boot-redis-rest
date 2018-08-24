package com.javieraviles.springredis.services;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.javieraviles.springredis.entities.User;

@Service
public class UserService {

	@Autowired
    private RedisTemplate<String, User> userTemplate;

	private static final String REDIS_PREFIX_USERS = "users";

	private static final String REDIS_KEYS_SEPARATOR = ":";

	public List<User> findByPattern(final String pattern) {
		return getValueOperations().multiGet(userTemplate.keys(getRedisKey(pattern)));
	}

	public User findById(final String userId) {
		final User user = getValueOperations().get(getRedisKey(UUID.fromString(userId).toString()));
		if(user == null) {
			throw new NotFoundException("User does not exist in the DB");
		}
		return user;
	}

	public void save(final User user) {
		user.setId(UUID.randomUUID().toString());
		getValueOperations().set(getRedisKey(user.getId()), user);
	}

	public void update(final User user) {
		findById(user.getId());
		getValueOperations().set(getRedisKey(user.getId()), user);
	}

	public void delete(final String userId) {
		if(!userTemplate.delete(getRedisKey(UUID.fromString(userId).toString()))) {
			throw new NotFoundException("User does not exist in the DB");
		}
	}

	private String getRedisKey(final String userId) {
        return REDIS_PREFIX_USERS + REDIS_KEYS_SEPARATOR + userId;
    }

	private ValueOperations<String, User> getValueOperations() {
		return userTemplate.opsForValue();
	}

}
