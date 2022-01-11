package com.hoonjin.sample.user.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            switch (response.status()) {
                case 400:
                    return null;
                case 404:
                    if (methodKey.contains("getOrders")) {
                        return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User's orders is empty.");
                    }
                    return null;
                default:
                    return new Exception(response.reason());
            }
        };
    }
}
