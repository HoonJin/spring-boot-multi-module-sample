package com.hoonjin.sample.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleUserApplication.class);
    }
}
