package com.example.softassign2api.Models.Notification;

public class CancelNotification extends NotificationTemplate {
    public CancelNotification(){
        template = "Your booking has been cancelled";
    }

    @Override
    public String createBody(String recipient,String language) {
        return "Your booking with id " + recipient + " has been cancelled. in "+language;
    }
}
