package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.model.Movie;

public interface ModelMapper {

    static MovieDto fromMovieToMovieDto(Movie movie) {
        return movie == null ? null : MovieDto.builder()
                .id(movie.getId())
                .age(movie.getAge())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .price(movie.getPrice())
                .releaseDate(movie.getReleaseDate())
                .title(movie.getTitle())
                .build();
    }

    static Movie fromMovieDtoToMovie(MovieDto movieDto) {
        return movieDto == null ? null : Movie.builder()
                .id(movieDto.getId())
                .age(movieDto.getAge())
                .description(movieDto.getDescription())
                .genre(movieDto.getGenre())
                .price(movieDto.getPrice())
                .releaseDate(movieDto.getReleaseDate())
                .title(movieDto.getTitle())
                .build();
    }

}
