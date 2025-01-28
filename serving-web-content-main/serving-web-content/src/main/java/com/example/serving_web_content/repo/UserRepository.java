package com.example.serving_web_content.repo;

import com.example.serving_web_content.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
