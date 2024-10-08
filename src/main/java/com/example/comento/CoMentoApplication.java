package com.example.comento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoMentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoMentoApplication.class, args);
    }

}
