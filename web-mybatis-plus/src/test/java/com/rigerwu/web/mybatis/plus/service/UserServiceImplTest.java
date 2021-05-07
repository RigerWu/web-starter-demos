package com.rigerwu.web.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rigerwu.web.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * created by riger on 2021/2/1
 */

@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void userServiceTest() {
        List<User> list = userService.lambdaQuery().ge(User::getAge, 20).list();
        Assertions.assertEquals(5, list.size());
        list.forEach(user -> log.info(user.toString()));
    }

    @Test
    public void deleteTest() {
        log.info("before delete count:" + userService.count());
        boolean b = userService.removeById(1);
        Assertions.assertTrue(b);
        log.info("after delete count:" + userService.count());

        User byId = userService.getById(1);
        Assertions.assertNull(byId);
    }

    @Test
    public void testPage() {
        Page<User> page = userService.page(new Page<>(1, 2));
        page.getRecords().forEach(user -> log.info(user.toString()));
        Assertions.assertEquals(2, page.getSize());
    }
}
