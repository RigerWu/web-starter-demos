package com.rigerwu.web.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * created by riger on 2021/2/1
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.rigerwu.web.mybatis.plus.mapper"})
public class WebMybatisPlusApplication {
    public static void main(String[] args){
        SpringApplication.run(WebMybatisPlusApplication.class, args);
    }
}
