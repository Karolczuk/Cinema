package com.app.cinema.controller;

import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.service.RepertoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repertoire")
public class RepertoireController {

    private final RepertoireService repertoireService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RepertoireDto addRepertoire(@RequestBody RepertoireDto repertoireDto) {
        return repertoireService.add(repertoireDto);
    }

    @GetMapping("/{movieId}")
    public List<RepertoireDto> findByMovieId(@PathVariable Long movieId) {
        return repertoireService.findAll(movieId);
    }


    @GetMapping("/{movieId}/{date}")
    public List<RepertoireDto> findByMovieIdAndDate(@PathVariable Long movieId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return repertoireService.findByMovieIdAndDate(movieId, date);
    }

}
