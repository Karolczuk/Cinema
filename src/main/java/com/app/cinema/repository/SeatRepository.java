package com.app.cinema.repository;

import com.app.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    //  List<Seat> findByReservationIdMovieIdAndReservationTime(Long movieId, LocalTime time, LocalDate date);

//    List<Seat> findByReservationMovieIdAndReservationTime(Long movieId, LocalDateTime time);

    // Optional<Seat> findByDateAndTimeAndAndColumnNumberAndRowNumberAndMovieId(LocalDate date, LocalTime time, Integer column, Integer raw, Long movieId);

    // List<Seat> findByDateAndTimeAndMovie_Id(LocalDate date, LocalTime time, Long movieId);
    Optional<Seat> findByRepertoireDateAndRepertoireTimeAndColumnCountAndRowCountAndRepertoireMovieId(LocalDate date, LocalTime time, Integer column, Integer raw, Long movieId);

    List<Seat> findByRepertoireDateAndRepertoireTimeAndRepertoireMovieId(LocalDate date, LocalTime time, Long movieId);

    List<Seat> findByRepertoireId(Long repertoireId);


    List<Seat> findByRepertoireIdAndUserUsername(Long repertoireId, String username);

}
