package com.app.cinema.controller;
import com.app.cinema.dto.Info;
import com.app.cinema.dto.MovieDto;
import com.app.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Info<List<MovieDto>>> findAll() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("MAGDA", "KAROLCZUK");

        return new ResponseEntity<>(
                Info.<List<MovieDto>>builder().data(movieService.findAll()).build(),
                headers,
                HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Info<MovieDto>> findOne(@PathVariable Long id) {

        return ResponseEntity.ok(Info.<MovieDto>builder().data(movieService.findOne(id)).build());

    }

    @PostMapping
    public ResponseEntity<Info<MovieDto>> add(RequestEntity<MovieDto> request) {

        HttpHeaders headers = request.getHeaders();
        System.out.println("--------------------------------");
        System.out.println(headers);
        MovieDto insertedMovie = movieService.add(request.getBody());

        return new ResponseEntity<>(
                Info.<MovieDto>builder().data(insertedMovie).build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Info<MovieDto>> update(@PathVariable Long id, RequestEntity<MovieDto> request) {
        return new ResponseEntity<>(
                Info.<MovieDto>builder().data(movieService.update(id, request.getBody())).build(),
                HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Info<MovieDto>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                Info.<MovieDto>builder().data(movieService.deleteById(id)).build(),
                HttpStatus.CREATED);
    }

}


