package com.app.cinema.repository;

import com.app.cinema.model.Booking;
import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
