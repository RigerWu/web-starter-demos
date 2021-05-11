package com.rigerwu.cache.redis.controller;

import com.rigerwu.cache.redis.entity.User;
import com.rigerwu.cache.redis.service.UserService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Riger on 2021/5/11
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    UserService userService;

    @GetMapping("/spring")
    public User getUserBySpringData(@RequestParam("id") Long id) {
        return userService.getUserById(id);
    }

    @SneakyThrows
    @GetMapping("/manual")
    public User getUserByPureRedis(@RequestParam("id") Long id) {
        return userService.getUserByIdRedis(id);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        return userService.delete(id);
    }

}
