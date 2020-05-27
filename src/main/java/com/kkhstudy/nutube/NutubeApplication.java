package com.kkhstudy.nutube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class NutubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutubeApplication.class, args);
    }

}
