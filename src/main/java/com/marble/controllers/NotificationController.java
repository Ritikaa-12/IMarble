package com.marble.controllers;

import com.marble.dto.NotificationDto;
import com.marble.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotification = notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(createdNotification);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST','DISPATCHER','CLIENT')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable Integer userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName(); // Assuming username is unique identifier like mobile/email
        List<String> roles = authentication.getAuthorities().stream()
                                .map(auth -> auth.getAuthority())
                                .toList();

        // If role CLIENT, restrict access to only their own notifications
        if (roles.contains("ROLE_CLIENT")) {
            // You must compare loggedInUsername with userId
            // For this you need a way to get the user's ID from username or vice versa
            // Assuming loggedInUsername is the userId as String
            if (!userId.toString().equals(loggedInUsername)) {
                throw new AccessDeniedException("You are not allowed to view other users' notifications");
            }
        }

        List<NotificationDto> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{notificationId}")
    public ResponseEntity<NotificationDto> updateNotification(
            @PathVariable Integer notificationId,
            @RequestBody NotificationDto notificationDto) {

        NotificationDto updatedNotification = notificationService.updateNotification(notificationId, notificationDto);
        return ResponseEntity.ok(updatedNotification);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Integer notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification deleted successfully!");
    }
}
