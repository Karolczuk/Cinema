package com.app.cinema.controller;

import com.app.cinema.dto.Info;
import com.app.cinema.dto.ReservationDto;
import com.app.cinema.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping
    public ResponseEntity<Info<ReservationDto>> add(RequestEntity<ReservationDto> request) {

        HttpHeaders headers = request.getHeaders();
        System.out.println("--------------------------------");
        System.out.println(headers);
        ReservationDto insertedReservation = reservationService.add(request.getBody());

        return new ResponseEntity<>(
                Info.<ReservationDto>builder().data(insertedReservation).build(),
                HttpStatus.CREATED);
    }
}
