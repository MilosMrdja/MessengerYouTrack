package com.messanger.infrstructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class YouTrackApiConfig {

    @Value("${youtrack.token}")
    private String token;

    @Value("${youtrack.api}")
    private String api;

    @Bean
    public RestTemplate youTrackRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + token);
            request.getHeaders().add("Accept", "application/json");
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(Collections.singletonList(authInterceptor));
        return restTemplate;
    }

    @Bean
    public String youTrackApiUrl() {
        return api;
    }
}
