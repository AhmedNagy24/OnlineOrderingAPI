package com.example.softassign2api.Database;
public interface NotificationDatabase {
    void  saveNotification(String recipient, String body);
    String getNotificationsQueue();
}
