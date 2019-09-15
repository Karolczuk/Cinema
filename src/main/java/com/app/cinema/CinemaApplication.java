package com.app.cinema;

import com.app.cinema.model.Movie;
import com.app.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaApplication implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var movie = Movie.builder().age(22).description("Movie").genre("COMEDY")
                .price(new BigDecimal(32.1))
                .releaseDate(LocalDateTime.now())
                .title("A")
                .duration(121.1)
                .build();

        movieRepository.saveAll(List.of( movie));
    }
}