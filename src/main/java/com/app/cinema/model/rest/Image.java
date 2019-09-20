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
public class Image {

    private  Long id;
    private List<Poster> posters;
    private List<Backdrop> backdrops;
}
