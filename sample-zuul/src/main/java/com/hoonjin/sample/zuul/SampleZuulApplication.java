package com.hoonjin.sample.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SampleZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleZuulApplication.class);
    }
}
