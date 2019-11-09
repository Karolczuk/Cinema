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
public class Discover {
    private Integer page;
    private List<MovieDetails> results;
    private Integer total_results;
    private Integer total_pages;
}
