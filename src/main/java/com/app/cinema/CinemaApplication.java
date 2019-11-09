package com.app.cinema;

import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
@EnableSwagger2
public class CinemaApplication implements CommandLineRunner {

//    private final MovieRepository movieRepository;
////    private final ReservationRepository reservationRepository;
//    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}