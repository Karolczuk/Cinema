package com.app.cinema.repository;

import com.app.cinema.model.PremiumUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremiumUserRepository extends JpaRepository<PremiumUser, Long> {
}
