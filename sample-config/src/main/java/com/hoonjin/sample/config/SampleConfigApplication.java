package com.hoonjin.sample.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SampleConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleConfigApplication.class);
    }
}
