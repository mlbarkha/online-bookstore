package com.amity.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amity.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContainingIgnoreCase(String title);

	List<Book> findByAuthorContainingIgnoreCase(String author);
}
