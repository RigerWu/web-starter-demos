package com.rigerwu.cache.redis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rigerwu.cache.redis.config.RedisUtils;
import com.rigerwu.cache.redis.entity.User;
import com.rigerwu.cache.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * created by Riger on 2021/3/18
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Resource
    RedisUtils redisUtils;
    @Resource
    ObjectMapper objectMapper;

    @Override
    @Cacheable(key = "#id")
    public User getUserById(Long id) {
        return mockDBService(id);
    }

    @Override
    public User getUserByIdRedis(Long id) throws JsonProcessingException {
        Object o = redisUtils.get("user:" + id);
        User user=null;
        if (o != null) {
            user = objectMapper.readValue((String) o, User.class);
        }
        if (user == null) {
            user = mockDBService(id);
            redisUtils.set("user:"+id, objectMapper.writeValueAsString(user));
        }
        return user;
    }

    @Override
    @CacheEvict(key = "#id")
    public String delete(Long id) {
        return "success";
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