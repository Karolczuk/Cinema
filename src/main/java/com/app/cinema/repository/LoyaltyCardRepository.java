package com.app.cinema.repository;

import com.app.cinema.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCardRepository extends JpaRepository<Card, Long> {
}
