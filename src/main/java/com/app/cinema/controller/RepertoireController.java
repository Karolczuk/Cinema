package com.app.cinema.controller;
import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.service.RepertoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<RepertoireDto> findByMovieIdAndDate(@PathVariable Long movieId, @PathVariable/* @DateTimeFormat(pattern = "dd-MM-yyyy")*/ String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return repertoireService.findByMovieIdAndDate(movieId, LocalDate.parse(date,formatter));
    }

    @GetMapping()
    public List<RepertoireDto> findByMovieIdsAndDate(@RequestParam List<Long> movieIds, @RequestParam/* @DateTimeFormat(pattern = "dd-MM-yyyy")*/ String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return repertoireService.findByMovieIdsAndDate(movieIds, LocalDate.parse(date,formatter));
    }

    @PutMapping("/{id}")
    public RepertoireDto update(@PathVariable Long id, @RequestBody RepertoireDto repertoireDto) {
        return repertoireService.update(id, repertoireDto);
    }

}
