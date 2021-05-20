package com.rigerwu.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * created by Riger on 2021/5/6
 */
@SpringBootApplication(scanBasePackages = {"com.rigerwu.cloud"})
@EnableDiscoveryClient
public class CloudProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudProviderApplication.class, args);
    }
}
