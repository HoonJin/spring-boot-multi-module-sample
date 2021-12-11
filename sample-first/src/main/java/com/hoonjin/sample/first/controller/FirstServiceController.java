package com.hoonjin.sample.first.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FirstServiceController {

    @GetMapping("/first-service/welcome")
    public String welcome() {
        return "welcome to the FIRST Service";
    }

    @GetMapping("/first-service/message")
    public String message(@RequestHeader("First-Request-Id") String requestId) {
        log.info("requestId = " + requestId);
        return "HELLO to the FIRST Service";
    }
}
