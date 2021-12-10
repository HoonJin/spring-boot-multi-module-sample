package com.hoonjin.sample.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to the FIRST Service";
    }
}
