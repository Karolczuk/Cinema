package com.app.cinema.controller;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.SeatDto;
import com.app.cinema.model.Seat;
import com.app.cinema.service.SeatService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public SeatDto reserveSeat(@RequestBody SeatDto seatDto) {
        return seatService.add(seatDto);
    }

    @GetMapping("/{movieId}/{date}/{time}")
    public List<SeatDto> findAllSeats(@PathVariable Long movieId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable @ApiParam(type = "String") String time) {
        return seatService.findAll(date, time, movieId);
    }

    @GetMapping("/{repertoireId}")
    public List<SeatDto> findAllSeatsByRepertoireId(@PathVariable Long repertoireId) {
        return seatService.findByRepertoireId(repertoireId);
    }

}
