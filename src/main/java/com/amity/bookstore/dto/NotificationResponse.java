package com.amity.bookstore.dto;

import java.time.LocalDateTime;

import com.amity.bookstore.utility.NotificationType;

import lombok.Data;

@Data
public class NotificationResponse {
    private Long id;
    private NotificationType type;
    private String message;
    private Boolean isRead;
    private LocalDateTime timestamp;
}
