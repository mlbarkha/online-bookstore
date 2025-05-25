package com.amity.bookstore.dto;

import java.time.LocalDateTime;

import com.amity.bookstore.utility.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private String paymentMethod;
    private LocalDateTime paymentDate;
}
