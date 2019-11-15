package com.app.cinema.repository;
import com.app.cinema.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByMovieId(Long movieId);

    List<Image> findByMovieIdIn(List<Long> movieIds);

}
