package com.messanger.application.interfaces;

import com.messanger.domain.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getNotifications();
}
