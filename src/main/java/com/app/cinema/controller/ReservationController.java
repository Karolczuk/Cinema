package com.app.cinema.controller;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.ReservationDto;
import com.app.cinema.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping
    public ReservationDto add(ReservationDto reservationDto) {
        return reservationService.add(reservationDto);

    }

    @GetMapping("/{page}/{size}")
    public Page<ReservationDto> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        return reservationService.findAll(PageRequest.of(page,size));

    }


}
