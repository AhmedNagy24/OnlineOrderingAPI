package com.example.softassign2api.Models;

public class Notification {
    NotificationTemplate notificationTemplate;
    public String _notify() {
        return this.notificationTemplate.createBody();
    }
}
