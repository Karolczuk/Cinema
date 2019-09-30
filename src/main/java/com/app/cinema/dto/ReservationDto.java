package com.app.cinema.dto;

import com.app.cinema.model.Movie;
import com.app.cinema.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {

    private Long id;
    private MovieDto movie;
    private UserDto user;

}
