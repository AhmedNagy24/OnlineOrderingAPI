package com.example.softassign2api.Models.Notification;

public class CancelNotification extends NotificationTemplate {

    public CancelNotification(String recipient) {
        super(recipient);
    }

    @Override
    public String createBody() {
        return "Your booking with id " + recipient + " has been cancelled.";
    }
}
