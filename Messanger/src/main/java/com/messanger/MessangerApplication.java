package com.messanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessangerApplication.class, args);
    }

}
