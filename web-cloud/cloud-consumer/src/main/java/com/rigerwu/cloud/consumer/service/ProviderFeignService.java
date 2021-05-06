package com.rigerwu.cloud.consumer.service;

/**
 * created by Riger on 2021/5/6
 */

import com.rigerwu.cloud.common.dto.ComplexDataDTO;
import com.rigerwu.cloud.common.dto.SimpleDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cloud-provider", path = "/provider")
public interface ProviderFeignService {

    @GetMapping("/simple")
    SimpleDataDTO simple();

    @PostMapping("complex")
    ComplexDataDTO complex(ComplexDataDTO dto);
}
