package com.messanger.infrstructure.client;

import com.messanger.domain.model.User;
import com.messanger.domain.port.AuthPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class AuthClient implements AuthPort {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    @Override
    public User getMe() {
        String url = apiBaseUrl + "/users/me?fields=id,name";

        return restTemplate.getForObject(url, User.class);
    }
}
