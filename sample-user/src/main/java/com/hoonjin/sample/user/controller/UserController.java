package com.hoonjin.sample.user.controller;

import com.hoonjin.sample.user.domain.RequestUser;
import com.hoonjin.sample.user.domain.ResponseUser;
import com.hoonjin.sample.user.domain.UserDto;
import com.hoonjin.sample.user.entity.User;
import com.hoonjin.sample.user.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Environment environment;
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser request) {
        UserDto userDto = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseUser.of(userDto));
    }

    @Timed(value = "users", longTask = true)
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<User> users = userService.getUserByAll();
        List<ResponseUser> result = StreamSupport.stream(users.spliterator(), false)
                .map(ResponseUser::of)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) {
        UserDto dto = userService.getUserByUserId(userId);
        ResponseUser result = ResponseUser.of(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return "working in userservice on PORT " + request.getServerPort();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }
}
