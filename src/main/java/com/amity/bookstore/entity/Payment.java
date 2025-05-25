package com.amity.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.amity.bookstore.utility.PaymentStatus;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;      // Reference to the Order

    private Long userId;       // Reference to the User

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentMethod; // e.g., "UPI", "CARD", "COD"

    private LocalDateTime paymentDate;
}
