package com.rigerwu.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * created by riger on 2021/1/9
 */
@SpringBootApplication
@ImportResource("classpath:provider.xml")
public class DubboProviderDemo {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderDemo.class, args);
    }
}
