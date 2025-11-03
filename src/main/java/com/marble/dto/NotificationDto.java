package com.marble.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Integer notificationId;
    private String type;
    private String message;
    private Integer target; // The userId this is intended for
}