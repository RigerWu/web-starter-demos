package com.rigerwu.cache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * created by Riger on 2021/3/18
 */
@SpringBootApplication
@EnableCaching
public class CacheRedisApplication {
    public static void main(String[] args){
        SpringApplication.run(CacheRedisApplication.class, args);
    }
}
