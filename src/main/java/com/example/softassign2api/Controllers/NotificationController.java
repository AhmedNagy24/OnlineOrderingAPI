package com.example.softassign2api.Controllers;

import com.example.softassign2api.Models.NotificationTemplate;
import com.example.softassign2api.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private  NotificationService notificationService;
    @GetMapping("/queue")
    public String getQueue() {
        return notificationService.getQueue();
    }
    @GetMapping("/mostNotifiedTemplate")
    public NotificationTemplate getMostNotifiedTemplate() {
        return notificationService.getTheMostNotifiedTemplate();
    }
    @GetMapping("/mostNotifiedCustomer")
    public String getMostNotifiedCustomer() {
        return notificationService.getTheMostNotifiedCustomer();
    }
}
