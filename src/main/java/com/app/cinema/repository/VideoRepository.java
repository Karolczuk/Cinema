package com.app.cinema.repository;

import com.app.cinema.dto.VideoDto;
import com.app.cinema.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByMovieId(Long movieId);

}
