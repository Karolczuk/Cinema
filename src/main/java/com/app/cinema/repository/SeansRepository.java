package com.app.cinema.repository;

import com.app.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeansRepository extends JpaRepository<Seat, Long> {
}
