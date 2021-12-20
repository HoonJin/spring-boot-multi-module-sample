package com.hoonjin.sample.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleCatalogApplication.class);
    }
}
