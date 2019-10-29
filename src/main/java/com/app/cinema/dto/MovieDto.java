package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String description;

}
