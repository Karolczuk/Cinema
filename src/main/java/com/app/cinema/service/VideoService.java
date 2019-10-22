package com.app.cinema.service;


import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.VideoDto;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Video;
import com.app.cinema.repository.VideoRepository;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoService {


    private final VideoRepository videoRepository;


    public Page<VideoDto> findAll(Pageable pageable) {
        Page<Video> videoPage = videoRepository.findAll(pageable);
        List<VideoDto> videos = videoPage.getContent()
                .stream()
                .map(ModelMapper::fromVideoToVideoDto)
                .collect(Collectors.toList());
        return new PageImpl<>(videos, videoPage.getPageable(), videoPage.getTotalElements());
    }

    public List<VideoDto> findVideoByMovieId(Long movieId) {
        return videoRepository.findByMovieId(movieId)
                .stream()
                .map(ModelMapper::fromVideoToVideoDto)
                .collect(Collectors.toList());
    }

}

