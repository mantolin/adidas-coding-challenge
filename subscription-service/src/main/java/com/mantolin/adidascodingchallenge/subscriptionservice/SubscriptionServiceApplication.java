package com.mantolin.adidascodingchallenge.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.mantolin.adidascodingchallenge.subscriptionservice.repository")
@SpringBootApplication
public class SubscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionServiceApplication.class, args);
    }
}
