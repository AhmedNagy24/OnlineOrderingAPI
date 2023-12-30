package com.example.softassign2api.Models.Notification;

public abstract class NotificationTemplate {
    protected String recipient;

    public NotificationTemplate(String recipient) {
        this.recipient = recipient;
    }

    public abstract String createBody();
}
