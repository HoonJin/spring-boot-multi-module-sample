package com.hoonjin.sample.user.service;

import com.hoonjin.sample.user.domain.RequestUser;
import com.hoonjin.sample.user.domain.UserDto;
import com.hoonjin.sample.user.entity.User;
import com.hoonjin.sample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(RequestUser request) {
        UserDto userDto = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .name(request.getName())
                .pwd(request.getPwd())
                .createdAt(LocalDateTime.now())
                .build();

        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .userId(userDto.getUserId())
                .createdAt(userDto.getCreatedAt())
                .build();
        user.setEncryptedPwd("encrypted");
        userRepository.save(user);

        return userDto;
    }
}
