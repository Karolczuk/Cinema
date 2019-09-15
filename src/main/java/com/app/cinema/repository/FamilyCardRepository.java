package com.app.cinema.repository;

import com.app.cinema.model.FamilyCard;
import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyCardRepository extends JpaRepository<FamilyCard, Long> {
}
