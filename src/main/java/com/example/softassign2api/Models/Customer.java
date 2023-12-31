package com.example.softassign2api.Models;

import com.example.softassign2api.Models.Notification.NotificationChannel;

public class Customer {
    private String userName;
    private String password;
    private double balance;
    private NotificationChannel notificationChannel;
    private String email;
    private String phoneNumber;
    private String language;

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public Customer(String userName, String password, double balance, NotificationChannel notificationChannel, String email, String phoneNumber, String language) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.notificationChannel = notificationChannel;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
