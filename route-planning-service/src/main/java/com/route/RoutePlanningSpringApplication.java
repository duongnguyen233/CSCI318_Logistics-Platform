package com.route;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RoutePlanningSpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(RoutePlanningSpringApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
