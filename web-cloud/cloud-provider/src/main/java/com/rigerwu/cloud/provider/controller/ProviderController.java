package com.rigerwu.cloud.provider.controller;

import com.rigerwu.cloud.common.dto.ComplexDataDTO;
import com.rigerwu.cloud.common.dto.SimpleDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * created by Riger on 2021/5/6
 */
@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController {

    @GetMapping("/simple")
    public SimpleDataDTO simple() {
        SimpleDataDTO simpleDataDTO = new SimpleDataDTO();
        simpleDataDTO.setData("This is a simple string from provider.");
        return simpleDataDTO;
    }

    @PostMapping("complex")
    public ComplexDataDTO complex(@RequestBody ComplexDataDTO dto) {
        log.info(dto.toString());
        dto.setDesc("Provider adds come content to the desc, then the json gets bigger.Provider adds come content to the desc, then the json gets bigger.Provider adds come content to the desc, then the json gets bigger.Provider adds come content to the desc, then the json gets bigger.");
        return dto;
    }
}
