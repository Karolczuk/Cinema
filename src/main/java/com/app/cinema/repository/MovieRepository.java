package com.app.cinema.repository;

import com.app.cinema.model.Movie;
import com.app.cinema.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleStartsWith(String title);
    Optional<Movie> findByTitle(String title);
    Page<Movie> findByTitleContaining(String title, Pageable pageable);

//    List<Movie> findMoviesByDiscoverModelId(Long discoverId);

}
