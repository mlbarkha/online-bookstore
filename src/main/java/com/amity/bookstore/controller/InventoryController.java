package com.amity.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amity.bookstore.dto.InventoryUpdateRequest;
import com.amity.bookstore.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/check")
    public ResponseEntity<String> checkStock(@RequestParam Long bookId, @RequestParam int quantity) {
        boolean isAvailable = inventoryService.checkStock(bookId, quantity);
        return ResponseEntity.ok(isAvailable ? "In Stock" : "Out of Stock");
    }

    @PostMapping("/reduce")
    public ResponseEntity<String> reduceStock(@RequestBody InventoryUpdateRequest request) {
        boolean result = inventoryService.reduceStock(request);
        return ResponseEntity.ok(result ? "Stock Updated" : "Not Enough Stock");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStock(@RequestParam Long bookId, @RequestParam int quantity) {
        inventoryService.addOrUpdateStock(bookId, quantity);
        return ResponseEntity.ok("Stock Added/Updated");
    }
}
