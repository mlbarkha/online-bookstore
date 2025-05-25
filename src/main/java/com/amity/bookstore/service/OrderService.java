package com.amity.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amity.bookstore.dto.InventoryUpdateRequest;
import com.amity.bookstore.dto.NotificationRequest;
import com.amity.bookstore.dto.OrderItemRequest;
import com.amity.bookstore.dto.OrderRequest;
import com.amity.bookstore.entity.Order;
import com.amity.bookstore.entity.OrderItem;
import com.amity.bookstore.repository.OrderRepository;
import com.amity.bookstore.utility.NotificationType;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private NotificationService notificationService;

	public Order createOrder(Long userId, Long bookId, int quantity) {
		Order order = new Order();
		order.setId(userId); 
		order.setId(bookId); 
		order.setQuantity(quantity);
		order.setStatus("PENDING");
		return orderRepository.save(order);
	}

	public List<Order> getOrdersByUser(Long userId) {
		return orderRepository.findByUserId(userId);
	}

	public List<Order> getOrdersByStatus(String status) {
		return orderRepository.findByStatus(status);
	}

	public Order updateOrderStatus(Long orderId, String status) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(status);
		return orderRepository.save(order);
	}
	
	public Order placeOrder(OrderRequest request) {
	    List<OrderItem> orderItems = new ArrayList<>();
	    double totalAmount = 0.0;

	    // 1. Check stock for each book
	    for (OrderItemRequest itemRequest : request.getItems()) {
	        boolean isAvailable = inventoryService.checkStock(itemRequest.getBookId(), itemRequest.getQuantity());

	        if (!isAvailable) {
	            throw new RuntimeException("Book ID " + itemRequest.getBookId() + " is out of stock.");
	        }

	        // Accumulate order details
	        totalAmount += itemRequest.getPrice() * itemRequest.getQuantity();

	        OrderItem item = OrderItem.builder()
	                .bookId(itemRequest.getBookId())
	                .quantity(itemRequest.getQuantity())
	                .price(itemRequest.getPrice())
	                .build();

	        orderItems.add(item);
	    }

	    // 2. Create Order
	    Order order = Order.builder()
	            .userId(request.getUserId())
	            .orderDate(LocalDateTime.now())
	            .status("PLACED")
	            .totalAmount(totalAmount)
	            .orderItems(orderItems)
	            .build();

	    orderItems.forEach(item -> item.setOrder(order)); // link items to order

	    Order savedOrder = orderRepository.save(order);

	    // 3. Reduce stock after order is saved
	    for (OrderItem item : savedOrder.getOrderItems()) {
	        InventoryUpdateRequest reduceRequest = new InventoryUpdateRequest();
	        reduceRequest.setBookId(item.getBookId());
	        reduceRequest.setQuantityToReduce(item.getQuantity());

	        boolean updated = inventoryService.reduceStock(reduceRequest);
	        if (!updated) {
	            throw new RuntimeException("Failed to update inventory for book ID " + item.getBookId());
	        }
	    }
	 // Send notification after order placement
	    NotificationRequest notificationRequest = new NotificationRequest();
	    notificationRequest.setUserId(request.getUserId());
	    notificationRequest.setType(NotificationType.ORDER);
	    notificationRequest.setMessage("Your order #" + savedOrder.getId() + " has been placed successfully!");

	    notificationService.sendNotification(notificationRequest);

	    return savedOrder;
	 

	}

}
