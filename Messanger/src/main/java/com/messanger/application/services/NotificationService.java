package com.messanger.application.services;

import com.messanger.application.interfaces.INotificationService;
import com.messanger.domain.model.EventNotification;
import com.messanger.domain.model.Notification;
import com.messanger.domain.port.NotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationPort notificationPort;

    @Value("${app.scheduler.notification.interval-minutes}")
    private final String intervalMinutes;

    @Override
    public List<Notification> getNotifications() {
        List<Notification> notifications = notificationPort.getNotifications();
        Instant nowUtc = Instant.now();
        Instant oneHourAgoUtc = nowUtc.minus(Long.parseLong(intervalMinutes), ChronoUnit.MINUTES);

        List<Notification> filteredNotifications = notifications.stream()
                .filter(notification -> {
                    Instant endTimeUtc = Instant.ofEpochMilli(notification.getChange().getEndTimestamp());
                    return !endTimeUtc.isBefore(oneHourAgoUtc);
                })
                .toList();

        printFormattedNotifications(filteredNotifications);
        return filteredNotifications;
    }

    private void printFormattedNotifications(List<Notification> notifications) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    NEW NOTIFICATIONS                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println("Number of notifications: " + notifications.size());
        System.out.println();

        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notifications.get(i);
            System.out.println("┌────────────────────── Notification " + (i + 1) + " ──────────────────────┐");

            System.out.println("│ 🔹 Type: " + notification.getType());
            System.out.println("│ 🔹 Summary: " + notification.getIssue().getSummary());

            String humanTime = notification.getChange().getHumanReadableTimeStamp();
            Instant instant = Instant.ofEpochMilli(notification.getChange().getEndTimestamp());
            String formattedTime = instant.atZone(ZoneId.of("Europe/Belgrade")).format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
            );

            System.out.println("│ 🔹 Time: " + humanTime + " (" + formattedTime + ")");

            List<EventNotification> events = notification.getChange().getEvents();
            System.out.println("│ 🔹 Events (" + events.size() + "):");

            for (EventNotification event : events) {
                String eventInfo = "   • " + event.getCategory();
                if (!event.getAddedValues().isEmpty()) {

                    String changes = event.getAddedValues().stream()
                            .map(v -> v.get("name").toString())
                            .collect(Collectors.joining("; "));

                    eventInfo += " (" + event.getAddedValues().size() + " changes) - " + changes;
                }
                System.out.println("│ " + eventInfo);
            }

            if (notification.getDescription() != null && !notification.getDescription().isEmpty()) {
                System.out.println("│ 🔹 Description: " +notification.getDescription());
            }

            if (notification.getIssue().getIdReadable() != null) {
                System.out.println("│ 🔹 ID: " + notification.getIssue().getIdReadable());
            }

            System.out.println("└────────────────────────────────────────────────────────────┘");
            System.out.println();
        }

        if (notifications.isEmpty()) {
            System.out.printf("ℹ️  There is no notifications in past %s minute(s)\n", intervalMinutes);
        }
    }

}
