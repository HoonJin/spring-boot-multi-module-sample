package com.hoonjin.sample.user.service;

import com.hoonjin.sample.user.domain.RequestUser;
import com.hoonjin.sample.user.domain.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void createUser() {
        String email = UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";
        RequestUser request = new RequestUser(email, "hello", "helloworld");

        UserDto user = userService.createUser(request);

        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getUserId()).isNotNull();
    }
}