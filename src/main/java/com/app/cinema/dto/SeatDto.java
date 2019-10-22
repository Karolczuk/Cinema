package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SeatDto {

    private Long id;
    private Integer columnNumber;
    private Integer rowNumber;
    private Integer roomNumber;
    private ReservationDto reservation;
    private UserDto user;
    private RepertoireDto repertoire;
    private LocalTime time;
    private LocalDate date;
}
