package com.app.cinema.repository;

import com.app.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {


    List<Seat> findByReservationMovieIdAndReservationTime(Long movieId, LocalDateTime time);
}
