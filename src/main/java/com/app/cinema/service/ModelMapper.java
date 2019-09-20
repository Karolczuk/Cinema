package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.ReservationDto;
import com.app.cinema.dto.TimeDto;
import com.app.cinema.dto.UserDto;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Reservation;
import com.app.cinema.model.Time;
import com.app.cinema.model.User;

public interface ModelMapper {

    static MovieDto fromMovieToMovieDto(Movie movie) {
        return movie == null ? null : MovieDto.builder()
                .id(movie.getId())
                .description(movie.getDescription())
               // .genre(movie.getGenre())
                .price(movie.getPrice())
                //.releaseDate(movie.getReleaseDate())
                .title(movie.getTitle())
                .build();
    }

    static Movie fromMovieDtoToMovie(MovieDto movieDto) {
        return movieDto == null ? null : Movie.builder()
                .id(movieDto.getId())
                //.age(movieDto.getAge())
                .description(movieDto.getDescription())
               // .genre(movieDto.getGenre())
                .price(movieDto.getPrice())
              //  .releaseDate(movieDto.getReleaseDate())
                .title(movieDto.getTitle())
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

    static TimeDto fromTimeToTimeDto(Time time) {
        return time == null ? null : TimeDto.builder()
                .localDateTime(time.getLocalDateTime())
                .build();
    }

    static Time fromTimeDtoToTime(TimeDto timeDto) {
        return timeDto == null ? null : Time.builder()
                .localDateTime(timeDto.getLocalDateTime())
                .build();
    }


    static ReservationDto fromReservationToReservationDto(Reservation reservation) {
        return reservation == null ? null : ReservationDto.builder()
                .id(reservation.getId())
                .time(reservation.getTime() == null ? null : fromTimeToTimeDto(reservation.getTime()))
                .movie(reservation.getMovie() == null ? null: fromMovieToMovieDto(reservation.getMovie()))
                .user(reservation.getUser() == null ? null : fromUserToUserDto(reservation.getUser()))
                .build();
    }


    static Reservation fromReservationDtoToReservation(ReservationDto reservationDto) {
        return reservationDto == null ? null : Reservation.builder()
                .id(reservationDto.getId())
                .time(reservationDto.getTime() == null ? null : fromTimeDtoToTime(reservationDto.getTime()))
                .movie(reservationDto.getMovie() == null ? null: fromMovieDtoToMovie(reservationDto.getMovie()))
                .user(reservationDto.getUser() == null ? null : fromUserDtoToUser(reservationDto.getUser()))
                .build();
    }

}
