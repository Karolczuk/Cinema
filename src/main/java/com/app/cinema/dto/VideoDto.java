package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
