package com.javieraviles.springredis.services;

import java.util.List;
import java.util.UUID;

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

	public List<User> findAll() {
		return getValueOperations().multiGet(userTemplate.keys(getRedisKey("*")));
	}

	public User findById(final String userId) {
		return getValueOperations().get(getRedisKey(userId));
	}

	public void save(final User user) {
		getValueOperations().set(getRedisKey(UUID.randomUUID().toString()), user);
	}

	public void delete(final String userId) {
		userTemplate.delete(getRedisKey(userId));
	}

	private String getRedisKey(final String userId) {
        return REDIS_PREFIX_USERS + REDIS_KEYS_SEPARATOR + userId;
    }

	private ValueOperations<String, User> getValueOperations() {
		return userTemplate.opsForValue();
	}

}
