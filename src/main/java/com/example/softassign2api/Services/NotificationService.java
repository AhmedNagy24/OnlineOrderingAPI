package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.NotificationDatabase;
import com.example.softassign2api.Models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    @Qualifier("inMemoryNotification")
    private NotificationDatabase InMemoryNotification;
    public String getQueue() {
        return InMemoryNotification.getNotificationsQueue();
    }
    public void saveNotification(String recipient, String body) {
        InMemoryNotification.saveNotification(recipient, body);
    }

}
