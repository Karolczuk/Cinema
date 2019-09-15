package com.app.cinema.repository;

import com.app.cinema.model.LoyaltyCard;
import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {
}
