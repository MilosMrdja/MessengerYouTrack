package com.messanger.infrstructure.client;

import com.messanger.domain.model.Notification;
import com.messanger.domain.model.NotificationEncoded;
import com.messanger.domain.model.Project;
import com.messanger.domain.port.NotificationPort;
import com.messanger.infrstructure.util.NotificationDecoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class NotificationClient implements NotificationPort {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    @Override
    public List<Notification> getNotifications() {
        String url = apiBaseUrl + "/notifications?fields=id,recipient(id),metadata";
        NotificationEncoded[] notificationArray = restTemplate.getForObject(url, NotificationEncoded[].class);
        List<NotificationEncoded> notificationsEncoded = notificationArray == null ? Collections.emptyList(): Arrays.asList(notificationArray);
        return NotificationDecoder.decode(notificationsEncoded);
    }
}
