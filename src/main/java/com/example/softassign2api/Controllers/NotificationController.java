package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint to get the notifications queue
    @GetMapping("/getQueue")
    public String getQueue() {
        return notificationService.getQueue();
    }
}