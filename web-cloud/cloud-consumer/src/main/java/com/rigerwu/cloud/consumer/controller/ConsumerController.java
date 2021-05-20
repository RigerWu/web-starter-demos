package com.rigerwu.cloud.consumer.controller;

import com.rigerwu.cloud.common.dto.ComplexDataDTO;
import com.rigerwu.cloud.common.dto.SimpleDataDTO;
import com.rigerwu.cloud.consumer.service.ProviderFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Riger on 2021/5/6
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    ProviderFeignService service;

    @GetMapping("/simple")
    public SimpleDataDTO simple() {
        return service.simple();
    }

    @GetMapping("/complex")
    public ComplexDataDTO complex() {
        ComplexDataDTO dto = new ComplexDataDTO();
        dto.setId(1);
        dto.setName("riger");
        dto.setOrder(0);
        dto.setScore(8.00f);
        dto.setUrl("https://www.rigerwu.com");
        return service.complex(dto);
    }
}
