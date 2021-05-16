package com.rigerwu.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * created by Riger on 2021/5/6
 */
@SpringBootApplication(scanBasePackages = {"com.rigerwu.cloud"})
@EnableDiscoveryClient
@EnableFeignClients
public class CloudConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudConsumerApplication.class, args);
    }
}
