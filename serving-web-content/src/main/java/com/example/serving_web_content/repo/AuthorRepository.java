package com.example.serving_web_content.repo;

import com.example.serving_web_content.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
