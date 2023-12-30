package com.example.softassign2api.Models.Notification;

public abstract class NotificationTemplate {


    public String getTemplate() {
        return template;
    }

    protected String template;

    public abstract String createBody(String recipient);

}
