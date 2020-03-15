package com.pisces.spring.boot.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootLearningEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearningEurekaApplication.class, args);
    }

}
