package com.rigerwu.web.mybatis.mapper;

import com.rigerwu.web.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * created by riger on 2021/1/23
 */
@Slf4j
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void insert() {
        User user = new User();
        user.setName("Tony");
        user.setAge(22);
        user.setEmail("tony@rigerwu.com");
        userDao.insert(user);
        log.info(user.toString());
        Assertions.assertTrue(user.getId() > 0);
    }

    @Test
    void deleteByPrimaryKey() {
        List<User> tony = userDao.queryByNameAndEmail("Tony", "tony@rigerwu.com");
        tony.forEach(user -> {
            int i = userDao.deleteByPrimaryKey(user.getId());
            Assertions.assertTrue(i > 0);
        });
    }

    @Test
    void selectByPrimaryKey() {
        User user = userDao.selectByPrimaryKey(1L);
        log.info(user.toString());
        Assertions.assertEquals((long) user.getId(), 1);
    }

    @Test
    void updateByPrimaryKeySelective() {
        User user = new User();
        user.setId(1L);
        user.setEmail("tom1@rigerwu.com");
        int i = userDao.updateByPrimaryKeySelective(user);
        Assertions.assertTrue( i > 0);
        User updatedUser = userDao.selectByPrimaryKey(1L);
        Assertions.assertTrue(updatedUser.getEmail().startsWith("tom1"));
    }

    @Test
    void queryByNameAndEmail() {
        List<User> jack = userDao.queryByNameAndEmail("Jack", "jack@rigerwu.com");
        Assertions.assertEquals(jack.size(), 1);
    }
}