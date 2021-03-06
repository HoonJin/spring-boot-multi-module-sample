package com.hoonjin.sample.user.service;

import com.hoonjin.sample.user.domain.RequestUser;
import com.hoonjin.sample.user.domain.ResponseOrder;
import com.hoonjin.sample.user.domain.UserDto;
import com.hoonjin.sample.user.entity.User;
import com.hoonjin.sample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final OrderServiceClient orderServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactoryCustomizer;

    public UserDto createUser(RequestUser request) {
        UserDto userDto = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .name(request.getName())
                .pwd(request.getPwd())
                .createdAt(LocalDateTime.now())
                .build();

        String encrypted = bCryptPasswordEncoder.encode(userDto.getPwd());

        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .userId(userDto.getUserId())
                .encryptedPwd(encrypted)
                .createdAt(userDto.getCreatedAt())
                .build();
        userRepository.save(user);

        return userDto;
    }

    public UserDto getUserByUserId(String userId) {
        UserDto userDto = userRepository.findByUserId(userId).map(u ->
                UserDto.builder()
                        .userId(u.getUserId())
                        .email(u.getEmail())
                        .name(u.getName())
                        .createdAt(u.getCreatedAt())
                        .build()
        ).orElseThrow();

        log.info("before call order service");
        CircuitBreaker circuitbreaker = circuitBreakerFactoryCustomizer.create("circuitbreaker");
        List<ResponseOrder> orders = circuitbreaker.run(() -> orderServiceClient.getOrders(userId), throwable -> List.of());
        log.info("after call order service");
        userDto.setOrders(orders);
        return userDto;
    }

    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user name is not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }

    public User getUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
