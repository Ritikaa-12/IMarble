package com.marble.service;

import com.marble.dto.NotificationDto;
import java.util.List;

public interface NotificationService {

   
    NotificationDto createNotification(NotificationDto notificationDto);

    
    List<NotificationDto> getNotificationsForUser(Integer userId);

   
    void deleteNotification(Integer notificationId);
}