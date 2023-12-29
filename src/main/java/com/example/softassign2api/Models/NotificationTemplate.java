package com.example.softassign2api.Models;

public abstract class NotificationTemplate {
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    private String recipient;

}
