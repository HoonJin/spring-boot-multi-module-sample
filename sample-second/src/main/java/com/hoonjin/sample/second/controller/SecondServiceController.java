package com.hoonjin.sample.second.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to the SECOND Service";
    }
}
