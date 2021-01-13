package com.rigerwu.web.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * created by riger on 2021/1/13
 */
@RestController
@Slf4j
public class LogController {

    @GetMapping("/log")
    public String hello() {
        String s = "log print test";
        log.debug(s);
        log.info(s);
        log.error(s);
        log.warn(s);
        return s;
    }
}
