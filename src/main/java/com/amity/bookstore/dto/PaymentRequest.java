package com.amity.bookstore.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private Long userId;
    private Double amount;
    private String paymentMethod; // e.g., CARD, UPI, COD
}
