package com.example.softassign2api.Models.Notification;

import com.example.softassign2api.Models.Customer;

import java.util.ArrayList;

public  class Notification {
    public static ArrayList<String> adaptMessage(Customer recipient) {
        ArrayList<String> channel = new ArrayList<>();
        if (recipient.getNotificationChannel() == NotificationChannel.EMAIL) {
            channel.add(recipient.getEmail());
        } else if (recipient.getNotificationChannel() == NotificationChannel.SMS) {
            channel.add(recipient.getPhoneNumber());
        } else if (recipient.getNotificationChannel() == NotificationChannel.BOTH) {
            channel.add(recipient.getPhoneNumber());
            channel.add(recipient.getEmail());
        }
        return channel;
    }
}
