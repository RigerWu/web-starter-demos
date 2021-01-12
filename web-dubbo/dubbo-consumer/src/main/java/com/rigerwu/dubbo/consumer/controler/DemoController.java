package com.rigerwu.dubbo.consumer.controler;

import com.rigerwu.dubbo.api.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by riger on 2021/1/10
 */

@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() {
        String result = null;
        try {
            result = demoService.sayHello("Riger");
            result  = "Consumer:"+port+ result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
