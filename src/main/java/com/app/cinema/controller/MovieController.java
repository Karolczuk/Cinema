package com.app.cinema.controller;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/{page}/{size}")
    public Page<MovieDto> findAllMovie(@PathVariable Integer page, @PathVariable Integer size, @RequestParam(required = false) String movieTitle) {
        return movieService.findAll(PageRequest.of(page, size),movieTitle);
    }


//    @GetMapping("/title/{title}")
//    public List<MovieDto> findMovieByTitle(@PathVariable String title) {
//
//        return movieService.findAll(title);
//
//    }

    @GetMapping("/{id}")
    public MovieDto findOne(@PathVariable Long id) {

        return movieService.findOne(id);

    }

    @PostMapping
    public MovieDto addMovie(@RequestBody MovieDto movieDto) {

        return movieService.add(movieDto);
    }

    @PutMapping("/{id}")
    public MovieDto update(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        return movieService.update(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieService.deleteById(id);
    }




}


