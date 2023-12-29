package com.example.softassign2api.Database;

import org.springframework.stereotype.Component;

import java.util.Queue;
@Component
public class InMemoryNotification implements NotificationDatabase{
    public static Queue<String> notificationsQueue;
    public InMemoryNotification() {
        notificationsQueue = new java.util.LinkedList<>();
        notificationsQueue.add("Welcome to the notification queue!");
    }
    @Override
    public void saveNotification(String recipient, String body) {
        notificationsQueue.add(body);
        //random time to simulate the sending then remove it from the queue
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notificationsQueue.remove(body);
    }

    @Override
    public String getNotificationsQueue() {
        String notifications = "";
        for (String notification : notificationsQueue) {
            notifications += (notification+"\n");
        }
        return notifications;
    }
}
