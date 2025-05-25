package com.amity.bookstore.dto;

import com.amity.bookstore.utility.NotificationType;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long userId;
    private NotificationType type;
    private String message;
}
