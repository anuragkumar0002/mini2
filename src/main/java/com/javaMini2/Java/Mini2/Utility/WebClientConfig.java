package com.javaMini2.Java.Mini2.Utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient1() {
        return WebClient.builder()
                .baseUrl("https://randomuser.me/api/")
                .build();
    }

    @Bean
    public WebClient webClient2() {
        return WebClient.builder()
                .baseUrl("https://api.nationalize.io/")
                .build();
    }

    @Bean
    public WebClient webClient3() {
        return WebClient.builder()
                .baseUrl("https://api.genderize.io/")
                .build();
    }
}
