package com.example.softassign2api.Models.Notification;

public class CancelNotification extends NotificationTemplate {

    @Override
    public String createBody(String recipient) {
        return "Your booking with id " + recipient + " has been cancelled.";
    }
}
