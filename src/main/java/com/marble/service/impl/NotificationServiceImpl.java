package com.marble.service.impl;

import com.marble.dto.NotificationDto;
import com.marble.entities.Notification;
import com.marble.repos.NotificationRepository;
import com.marble.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = this.dtoToEntity(notificationDto);
        Notification savedNotification = notificationRepository.save(notification);
        return this.entityToDto(savedNotification);
    }

    @Override
    public List<NotificationDto> getNotificationsForUser(Integer userId) {
        List<Notification> notifications = notificationRepository.findByTarget(userId);
        return notifications.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
        notificationRepository.delete(notification);
    }

    // Helper method to convert Entity to DTO
    private NotificationDto entityToDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setNotificationId(notification.getNotificationId());
        dto.setType(notification.getType());
        dto.setMessage(notification.getMessage());
        dto.setTarget(notification.getTarget());
        return dto;
    }

    // Helper method to convert DTO to Entity
    private Notification dtoToEntity(NotificationDto dto) {
        Notification notification = new Notification();
        notification.setType(dto.getType());
        notification.setMessage(dto.getMessage());
        notification.setTarget(dto.getTarget());
        return notification;
    }
}