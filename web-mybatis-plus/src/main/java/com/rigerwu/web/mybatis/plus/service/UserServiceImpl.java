package com.rigerwu.web.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rigerwu.web.mybatis.plus.entity.User;
import com.rigerwu.web.mybatis.plus.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * created by riger on 2021/2/1
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
