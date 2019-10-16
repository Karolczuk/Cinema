package com.app.cinema.repository;

import com.app.cinema.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByMovieId(Long movieId);

}
