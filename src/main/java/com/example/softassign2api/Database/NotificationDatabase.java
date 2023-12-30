package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Models.NotificationTemplate;

public interface NotificationDatabase {
    void saveNotification(Customer recipient, NotificationTemplate notificationTemplate);
    String getNotifications();
    NotificationTemplate getMostNotifiedTemplate();
    String getMostNotifiedCustomer();

}
