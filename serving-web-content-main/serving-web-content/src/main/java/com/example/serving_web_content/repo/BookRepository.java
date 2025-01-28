package com.example.serving_web_content.repo;

import com.example.serving_web_content.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
