package com.app.cinema.controller;

import com.app.cinema.dto.ImageDto;
import com.app.cinema.model.Image;
import com.app.cinema.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{movieId}")
    public List<ImageDto> findImageByMovie(@PathVariable Long movieId) {
        return imageService.findImageByMovieId(movieId);
    }

    @GetMapping("/all/{page}/{size}")
    public Page<ImageDto> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return imageService.findAll(PageRequest.of(page, size));
    }

    @GetMapping
    public List<ImageDto> findImagesByMovieIdIn(@RequestParam List<Long> movieIds) {
        return imageService.findImagesByMovieIdIn(movieIds);
    }
}
