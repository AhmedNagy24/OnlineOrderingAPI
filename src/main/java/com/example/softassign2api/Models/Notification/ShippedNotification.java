package com.example.softassign2api.Models.Notification;

public class ShippedNotification extends NotificationTemplate {
    public ShippedNotification(String recipient) {
        super(recipient);
    }

    @Override
    public String createBody() {
        return "customer" + recipient + "your order has been shipped";
    }
}
