package com.amity.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amity.bookstore.dto.InventoryUpdateRequest;
import com.amity.bookstore.entity.Inventory;
import com.amity.bookstore.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean checkStock(Long bookId, int requiredQty) {
        return inventoryRepository.findByBookId(bookId)
                .map(inventory -> inventory.getQuantityAvailable() >= requiredQty)
                .orElse(false);
    }

    public boolean reduceStock(InventoryUpdateRequest request) {
        Inventory inventory = inventoryRepository.findByBookId(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found in inventory"));

        if (inventory.getQuantityAvailable() < request.getQuantityToReduce()) {
            return false; // not enough stock
        }

        inventory.setQuantityAvailable(inventory.getQuantityAvailable() - request.getQuantityToReduce());
        inventoryRepository.save(inventory);
        return true;
    }

    public void addOrUpdateStock(Long bookId, int quantityToAdd) {
        Inventory inventory = inventoryRepository.findByBookId(bookId)
                .orElse(Inventory.builder().bookId(bookId).quantityAvailable(0).build());

        inventory.setQuantityAvailable(inventory.getQuantityAvailable() + quantityToAdd);
        inventoryRepository.save(inventory);
    }
}
