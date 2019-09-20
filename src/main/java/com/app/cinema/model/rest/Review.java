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
public class Review {

    private Long id;
    private Integer page;
    private List<ReviewResult> results;
    private Integer total_pages;
    private Integer total_results;
}
