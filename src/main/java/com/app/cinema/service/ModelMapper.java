package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.ReservationDto;
import com.app.cinema.dto.UserDto;
import com.app.cinema.dto.VideoDto;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Reservation;
import com.app.cinema.model.User;
import com.app.cinema.model.Video;

public interface ModelMapper {

    static MovieDto fromMovieToMovieDto(Movie movie) {
        return movie == null ? null : MovieDto.builder()
                .id(movie.getId())
                .description(movie.getDescription())
                .price(movie.getPrice())
                .title(movie.getTitle())
                .build();
    }

    static Movie fromMovieDtoToMovie(MovieDto movieDto) {
        return movieDto == null ? null : Movie.builder()
                .id(movieDto.getId())
                .description(movieDto.getDescription())
                .price(movieDto.getPrice())
                .title(movieDto.getTitle())
                .build();
    }

    static VideoDto fromVideoToVideoDto(Video video) {
        return video == null ? null : VideoDto.builder()
                .id(video.getId())
                .name(video.getName())
                .keyHash(video.getKeyHash())
                .site(video.getSite())
                .movie(video.getMovie() == null ? null : fromMovieToMovieDto(video.getMovie()))
                .build();
    }

    static UserDto fromUserToUserDto(User user) {
        return user == null ? null : UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    static User fromUserDtoToUser(UserDto userDto) {
        return userDto == null ? null : User.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build();
    }


    static ReservationDto fromReservationToReservationDto(Reservation reservation) {
        return reservation == null ? null : ReservationDto.builder()
                .id(reservation.getId())
                .movieId(reservation.getMovie().getId())
                .movieDate(reservation.getTime())
                .build();
    }


    static Reservation fromReservationDtoToReservation(ReservationDto reservationDto) {
        return reservationDto == null ? null : Reservation.builder()
                .id(reservationDto.getId())
                .time(reservationDto.getMovieDate())
                .build();
    }

}
