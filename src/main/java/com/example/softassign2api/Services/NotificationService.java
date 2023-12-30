package com.example.softassign2api.Services;

import com.example.softassign2api.Database.NotificationDB.INotificationDatabase;
import com.example.softassign2api.Models.Notification.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    @Qualifier("inMemoryNotification")
    private INotificationDatabase InMemoryNotification;

    public String getQueue() {
        return InMemoryNotification.getNotifications();
    }

    public String getTheMostNotifiedTemplate() {
        return InMemoryNotification.getMostNotifiedTemplate();
    }

    public String getTheMostNotifiedCustomer() {
        return InMemoryNotification.getMostNotifiedCustomer();
    }


}
