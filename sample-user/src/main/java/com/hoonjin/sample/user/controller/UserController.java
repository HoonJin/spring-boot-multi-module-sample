package com.hoonjin.sample.user.controller;

import com.hoonjin.sample.user.domain.RequestUser;
import com.hoonjin.sample.user.domain.UserDto;
import com.hoonjin.sample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Environment environment;
    private final UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@RequestBody RequestUser request) {
        return userService.createUser(request);
    }

    @GetMapping("/user-service/health_check")
    public String status() {
        return "working in userservice";
    }

    @GetMapping("/user-service/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }
}
