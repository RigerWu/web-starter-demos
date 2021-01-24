package com.rigerwu.web.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.rigerwu.web.mybatis.mapper"})
public class WebMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMybatisApplication.class, args);
    }

}
