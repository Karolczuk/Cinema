package com.app.cinema.repository;
import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {

    List<Repertoire> findByMovieId(Long movieId);
    List<Repertoire> findByMovieIdAndDate(Long movieId, LocalDate date);

}
