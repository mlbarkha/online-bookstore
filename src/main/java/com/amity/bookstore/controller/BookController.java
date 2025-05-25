package com.amity.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amity.bookstore.entity.Book;
import com.amity.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam String keyword) {
		return bookService.searchBooks(keyword);
	}

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		return bookService.updateBook(id, bookDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
}
