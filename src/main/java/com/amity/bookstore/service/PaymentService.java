package com.amity.bookstore.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amity.bookstore.dto.PaymentRequest;
import com.amity.bookstore.dto.PaymentResponse;
import com.amity.bookstore.entity.Payment;
import com.amity.bookstore.repository.PaymentRepository;
import com.amity.bookstore.utility.PaymentStatus;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponse makePayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(PaymentStatus.SUCCESS) // Simulating successful payment
                .paymentDate(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(saved.getId());
        response.setOrderId(saved.getOrderId());
        response.setAmount(saved.getAmount());
        response.setStatus(saved.getStatus());
        response.setPaymentMethod(saved.getPaymentMethod());
        response.setPaymentDate(saved.getPaymentDate());
        return response;
    }
}
