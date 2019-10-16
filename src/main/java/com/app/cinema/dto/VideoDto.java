package com.app.cinema.dto;

import com.app.cinema.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDto {

    private Long id;

    private String name;
    private String site;
    private String keyHash;
    private MovieDto movie;
}
