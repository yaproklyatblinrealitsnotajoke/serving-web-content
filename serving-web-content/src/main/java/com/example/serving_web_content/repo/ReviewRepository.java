package com.example.serving_web_content.repo;

import com.example.serving_web_content.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
