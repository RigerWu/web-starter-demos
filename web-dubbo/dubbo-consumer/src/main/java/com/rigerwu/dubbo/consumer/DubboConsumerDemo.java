package com.rigerwu.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * created by riger on 2021/1/10
 */
@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class DubboConsumerDemo {
    public static void main(String[] args){
        SpringApplication.run(DubboConsumerDemo.class, args);
    }
}
