package com.rigerwu.web.mybatis.plus.mapper;

import com.rigerwu.web.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by riger on 2021/2/1
 */
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        Assertions.assertTrue(users.size() > 0);
        users.forEach(user -> log.info(user.toString()));
    }
}