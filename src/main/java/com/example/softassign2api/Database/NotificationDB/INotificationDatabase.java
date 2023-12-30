package com.example.softassign2api.Database.NotificationDB;

import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Models.Notification.NotificationTemplate;

public interface INotificationDatabase {
    void saveNotification(Customer recipient, NotificationTemplate notificationTemplate);

    String getNotifications();

    String getMostNotifiedTemplate();

    String getMostNotifiedCustomer();

}
