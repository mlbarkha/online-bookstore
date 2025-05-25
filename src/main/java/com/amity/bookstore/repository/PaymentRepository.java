package com.amity.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amity.bookstore.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
    Optional<Payment> findByOrderId(Long orderId);
}
