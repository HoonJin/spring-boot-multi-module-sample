package com.hoonjin.sample.first.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FirstServiceController {

    private final Environment environment;

    @GetMapping("/first-service/welcome")
    public String welcome() {
        return "welcome to the FIRST Service: " + environment.getProperty("local.server.port");
    }

    @GetMapping("/first-service/message")
    public String message(@RequestHeader("First-Request-Id") String requestId) {
        log.info("requestId = " + requestId);
        return "HELLO to the FIRST Service"  + environment.getProperty("local.server.port");
    }
}
