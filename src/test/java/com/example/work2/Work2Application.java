package com.example.work2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.work2.repository")
public class Work2Application {
    public static void main(String[] args) {
        SpringApplication.run(Work2Application.class, args);
    }
}