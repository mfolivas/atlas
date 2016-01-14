package com.mfolivas.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class AtlasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtlasApplication.class, args);
    }
}
