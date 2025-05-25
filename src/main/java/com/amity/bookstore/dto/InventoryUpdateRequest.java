package com.amity.bookstore.dto;

import lombok.Data;

@Data
public class InventoryUpdateRequest {
    private Long bookId;
    private Integer quantityToReduce; // e.g., if someone orders 2 books
}