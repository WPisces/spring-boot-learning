package com.pisces.spring.boot.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRetry
@EnableAsync
public class SpringBootLearningConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearningConsumerApplication.class, args);
    }

}
