package com.example.softassign2api.Models;

import lombok.Setter;

import java.util.Queue;


public abstract class NotificationTemplate {
    protected String recipient;

    public NotificationTemplate(String recipient) {
        this.recipient = recipient;
    }

    public abstract String createBody();
}
