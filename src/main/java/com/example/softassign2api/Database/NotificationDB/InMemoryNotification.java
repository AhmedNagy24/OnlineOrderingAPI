package com.example.softassign2api.Database.NotificationDB;

import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Models.Notification.Notification;
import com.example.softassign2api.Models.Notification.NotificationTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryNotification implements INotificationDatabase {
    private static Queue<String> notificationsQueue;
    private static Map<String, Integer> notificationStatistics;
    private static Map<NotificationTemplate, Integer> notificationTemplateStastics;

    static {
        notificationsQueue = new java.util.LinkedList<>();
        notificationsQueue.add("Welcome to the notification queue!");
        notificationStatistics = new java.util.HashMap<>();
        notificationTemplateStastics = new java.util.HashMap<>();
    }

    public void saveNotification(Customer recipient, NotificationTemplate notificationTemplate) {
        ArrayList<String> channels = Notification.adaptMessage(recipient);
        updateNotificationTemplateStatistics(notificationTemplate);
        for (String channel : channels) {
            String notification = notificationTemplate.createBody(channel);
            notificationsQueue.add(notification);
            updateNotificationStatistics(channel);
        }

        new NotificationPopDelay(90000);
    }

    private void updateNotificationStatistics(String recipient) {
        if (notificationStatistics.containsKey(recipient)) {
            notificationStatistics.put(recipient, notificationStatistics.get(recipient) + 1);
        } else {
            notificationStatistics.put(recipient, 1);
        }
    }

    private void updateNotificationTemplateStatistics(NotificationTemplate notificationTemplate) {
        if (notificationTemplateStastics.containsKey(notificationTemplate)) {
            notificationTemplateStastics.put(notificationTemplate, notificationTemplateStastics.get(notificationTemplate) + 1);
        } else {
            notificationTemplateStastics.put(notificationTemplate, 1);
        }
    }

    public String getNotifications() {
        //loop through the queue and return all the contents in a string
        StringBuilder stringBuilder = new StringBuilder();
        while (!notificationsQueue.isEmpty()) {
            stringBuilder.append(notificationsQueue.poll());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    //function to return the most notifed template
    public String getMostNotifiedTemplate() {
        NotificationTemplate mostNotifiedTemplate = null;
        int max = 0;
        for (Map.Entry<NotificationTemplate, Integer> entry : notificationTemplateStastics.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostNotifiedTemplate = entry.getKey();
            }
        }
        return mostNotifiedTemplate.getTemplate();
    }

    //function to return the most notified customer
    public String getMostNotifiedCustomer() {
        String mostNotifiedCustomer = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : notificationStatistics.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostNotifiedCustomer = entry.getKey();
            } else if (entry.getValue() == max) {
                mostNotifiedCustomer = mostNotifiedCustomer + ", " + entry.getKey();
            }
        }
        return mostNotifiedCustomer;
    }

    public static Queue<String> getNotificationsQueue() {
        return notificationsQueue;
    }
}

class NotificationPopDelay extends TimerTask {
    public NotificationPopDelay(int delay) {
        Timer timer = new Timer();
        timer.schedule(this, delay);
    }
    @Override
    public void run() {
        InMemoryNotification.getNotificationsQueue().poll();
    }
}