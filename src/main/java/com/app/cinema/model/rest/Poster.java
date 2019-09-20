package com.app.cinema.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Poster {

    private Double aspect_ratio;
    private String file_path;
    private Integer height;
    private String iso_639_1;
    private Integer vote_average;
    private Integer vote_count;
    private Integer width;
}
