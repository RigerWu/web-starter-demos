package com.rigerwu.dubbo.provider.service;

import com.rigerwu.dubbo.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * created by riger on 2021/1/9
 */
@Slf4j
public class DefaultDemoServiceImpl implements DemoService {

    @Value("${server.port}")
    private String port;

    @Override
    public String sayHello(String name) {
        return String.format(" Hello, %s ,Provicer:%s %s", name, port, System.currentTimeMillis());
    }
}
