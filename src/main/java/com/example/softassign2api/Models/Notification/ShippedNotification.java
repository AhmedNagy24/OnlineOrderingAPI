package com.example.softassign2api.Models.Notification;

public class ShippedNotification extends NotificationTemplate {
    @Override
    public String createBody(String recipient) {
        return "customer" + recipient + "your order has been shipped";
    }
}
