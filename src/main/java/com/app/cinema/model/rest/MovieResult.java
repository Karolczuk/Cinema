package com.app.cinema.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResult {

    private String description;
    private Integer favorite_count;
    private Long id;
    private Long item_count;
    private String iso_639_1;
    private String list_type;
    private String name;
    private String poster_path;
}
