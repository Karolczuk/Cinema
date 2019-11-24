package com.app.cinema.dto;

import com.app.cinema.model.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private Long id;
    private String title;
    private Integer duration;
    @JsonFormat(pattern = "d.MM.yyyy")
    @DateTimeFormat(pattern = "d.MM.yyyy")
    private LocalDate releaseDate;
    private BigDecimal price;
    private String description;
    private Set<String> genres;
    private DiscoverDto discoverDto;

}
