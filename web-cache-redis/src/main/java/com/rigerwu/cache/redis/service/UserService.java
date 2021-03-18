package com.rigerwu.cache.redis.service;

import com.rigerwu.cache.redis.entity.User;

/**
 * created by Riger on 2021/3/18
 */
public interface UserService {

    User getUserById(Long id);

}
