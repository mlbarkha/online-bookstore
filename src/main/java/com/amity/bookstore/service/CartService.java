package com.amity.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amity.bookstore.entity.CartItem;
import com.amity.bookstore.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public List<CartItem> getCartItems(Long userId) {
		return cartRepository.findByUserId(userId);
	}

	public CartItem addToCart(CartItem cartItem) {
		return cartRepository.save(cartItem);
	}

	public void removeFromCart(Long id) {
		cartRepository.deleteById(id);
	}

	public void clearCart(Long userId) {
		cartRepository.deleteByUserId(userId);
	}
}
