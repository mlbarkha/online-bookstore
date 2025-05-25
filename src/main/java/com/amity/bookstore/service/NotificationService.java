package com.amity.bookstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amity.bookstore.dto.NotificationRequest;
import com.amity.bookstore.dto.NotificationResponse;
import com.amity.bookstore.entity.Notification;
import com.amity.bookstore.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .message(request.getMessage())
                .isRead(false)
                .timestamp(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(notification);

        return mapToResponse(saved);
    }

    public List<NotificationResponse> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private NotificationResponse mapToResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setType(notification.getType());
        response.setMessage(notification.getMessage());
        response.setIsRead(notification.getIsRead());
        response.setTimestamp(notification.getTimestamp());
        return response;
    }
}
