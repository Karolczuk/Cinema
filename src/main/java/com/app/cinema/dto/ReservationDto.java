package com.app.cinema.dto;

import com.app.cinema.model.Movie;
import com.app.cinema.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {

    private Long id;
    private Long movieId;
    private LocalDateTime movieDate;
    //private UserDto user; z tokena odczytujemy user

}
