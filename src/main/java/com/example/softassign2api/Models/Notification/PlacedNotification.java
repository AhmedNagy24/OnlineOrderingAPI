package com.example.softassign2api.Models.Notification;

public class PlacedNotification extends NotificationTemplate {

    @Override
    public String createBody(String recipient) {
        return "customer" + recipient + "your order has been placed";
    }
}
