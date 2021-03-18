package com.rigerwu.cache.redis.service.impl;

import com.rigerwu.cache.redis.entity.User;
import com.rigerwu.cache.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Riger on 2021/3/18
 */
@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void getUserById() {
        Long id = 10086L;
        User user = userService.getUserById(id);
        log.info(user.toString());
        assertTrue(redisTemplate.hasKey("user::" + id));
    }
}