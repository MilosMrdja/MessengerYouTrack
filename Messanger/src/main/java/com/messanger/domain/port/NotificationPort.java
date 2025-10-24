package com.messanger.domain.port;

import com.messanger.domain.model.Notification;
import com.messanger.domain.model.NotificationEncoded;

import java.util.List;

public interface NotificationPort {
    List<Notification> getNotifications();
}
