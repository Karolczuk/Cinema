package com.app.cinema.repository;

import com.app.cinema.model.Seans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeansRepository extends JpaRepository<Seans, Long> {
}
