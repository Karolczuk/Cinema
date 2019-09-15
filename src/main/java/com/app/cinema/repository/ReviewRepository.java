package com.app.cinema.repository;

import com.app.cinema.model.Review;
import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
