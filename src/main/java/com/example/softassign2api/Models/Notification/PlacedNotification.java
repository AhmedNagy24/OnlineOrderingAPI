package com.example.softassign2api.Models.Notification;

public class PlacedNotification extends NotificationTemplate {
    public PlacedNotification(){
        template = "customer {x} your order has been placed";
    }
    @Override
    public String createBody(String recipient) {
        return "customer" + recipient + "your order has been placed";
    }


}
