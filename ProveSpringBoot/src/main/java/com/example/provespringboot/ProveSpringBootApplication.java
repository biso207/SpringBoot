package com.example.provespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProveSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProveSpringBootApplication.class, args);
    }

}
