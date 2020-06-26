package com.d1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.d1"
        , "com.d1.controller"
        , "com.d1.model"
        , "com.d1.repository"})
public class PuzzleLauncher {

    public static void main(String[] args) {
        SpringApplication.run(PuzzleLauncher.class, args);
    }
}
