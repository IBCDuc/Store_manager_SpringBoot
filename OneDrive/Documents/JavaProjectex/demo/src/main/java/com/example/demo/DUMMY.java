package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.demo.entity"})
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
@EnableJpaAuditing
public class DUMMY {
    public static void main(String[] args) {
        SpringApplication.run(DUMMY.class, args);
    }
}