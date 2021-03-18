package com.rigerwu.cache.redis.service.impl;

import com.rigerwu.cache.redis.entity.User;
import com.rigerwu.cache.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * created by Riger on 2021/3/18
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(key = "#id")
    public User getUserById(Long id) {
        return mockDBService(id);
    }

    private User mockDBService(Long id) {
        log.info("load user: {} from db", id);
        User user = new User();
        user.setId(id);
        int i = new Random().nextInt(100);
        user.setName("Tom" + i);
        user.setAge(i);
        user.setEmail(id + "@rigerwu.com");
        return user;
    }
}