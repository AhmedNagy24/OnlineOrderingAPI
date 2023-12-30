package com.example.softassign2api.Models.Notification;

public class PlacedNotification extends NotificationTemplate {

    public PlacedNotification(String recipient) {
        super(recipient);
    }

    @Override
    public String createBody() {
        return "customer" + recipient + "your order has been placed";
    }
}
