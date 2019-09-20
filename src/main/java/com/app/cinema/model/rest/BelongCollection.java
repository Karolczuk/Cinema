package com.app.cinema.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BelongCollection {

    private Long id;
    private String name;
    private String poster_path;
    private String backdrop_path;
}
