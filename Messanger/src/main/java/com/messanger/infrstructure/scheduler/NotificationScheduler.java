package com.messanger.infrstructure.scheduler;

import com.messanger.application.interfaces.INotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final INotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);

    @Scheduled(fixedRateString = "${app.scheduler.notification.interval-minutes}",
            timeUnit = java.util.concurrent.TimeUnit.MINUTES)
    public void getNotifications() {
        try{
            logger.info("Fetching notifications...");
            notificationService.getNotifications();
            logger.info("Notifications fetched.");
            System.out.println("> ");
        }catch (Exception e){
            logger.error("Notification scheduler error: {}", e.getMessage());
        }

    }
}
