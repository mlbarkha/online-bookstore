package com.amity.bookstore.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long bookId;
    private Integer quantity;
    private Double price;
}
