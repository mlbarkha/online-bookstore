package com.amity.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amity.bookstore.dto.PaymentRequest;
import com.amity.bookstore.dto.PaymentResponse;
import com.amity.bookstore.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.makePayment(request);
        return ResponseEntity.ok(response);
    }
}
