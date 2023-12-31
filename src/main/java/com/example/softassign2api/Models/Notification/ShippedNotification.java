package com.example.softassign2api.Models.Notification;

public class ShippedNotification extends NotificationTemplate {
   public ShippedNotification(){
        template = "Your order { has been shipped";
    }
    @Override
    public String createBody(String recipient,String language) {
        return "customer" + recipient + "your order has been shipped in "+language;
    }
}
