package com.app.cinema.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieList {

    private Long id;
    private Integer page;
    private List<MovieResult> results;
    private Integer total_pages;
    private Integer total_results;
}
