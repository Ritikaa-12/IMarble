package com.marble.controllers;

import com.marble.dto.NotificationDto;
import com.marble.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // ---------------- CREATE NOTIFICATION ----------------
    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotification = notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(createdNotification);
    }

    // ---------------- GET NOTIFICATIONS FOR USER ----------------
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable Integer userId) {
        List<NotificationDto> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }
    
    @PutMapping("/update/{notificationId}")
    public ResponseEntity<NotificationDto> updateNotification(
            @PathVariable Integer notificationId,
            @RequestBody NotificationDto notificationDto) {

        NotificationDto updatedNotification = notificationService.updateNotification(notificationId, notificationDto);
        return ResponseEntity.ok(updatedNotification);
    }


    // ---------------- DELETE NOTIFICATION ----------------
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Integer notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification deleted successfully!");
    }
}
