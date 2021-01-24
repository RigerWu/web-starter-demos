package com.rigerwu.web.mybatis.controller;

import com.rigerwu.web.mybatis.entity.User;
import com.rigerwu.web.mybatis.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by riger on 2021/1/24
 */
@RestController
public class UserController {

    // 这里为演示,直接调的dao,实际开发应当使用service

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userDao.selectByPrimaryKey(id);
    }
}
