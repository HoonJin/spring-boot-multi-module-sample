package com.hoonjin.sample.second.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecondServiceController {

    @GetMapping("/second-service/welcome")
    public String welcome() {
        return "welcome to the SECOND Service";
    }

    @GetMapping("/second-service/message")
    public String message(@RequestHeader("Second-Request-Id") String requestId) {
        log.info("requestId = " + requestId);
        return "HELLO to the SECOND Service";
    }
}
