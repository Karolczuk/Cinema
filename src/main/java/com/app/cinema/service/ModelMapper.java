package com.app.cinema.service;

import com.app.cinema.dto.*;
import com.app.cinema.model.*;

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
                //    .movie(video.getMovie() == null ? null : fromMovieToMovieDto(video.getMovie()))
                .build();
    }

    static ImageDto fromImageToImageDto(Image image) {
        return image == null ? null : ImageDto.builder()
                .id(image.getId())
                .poster(image.getPoster())
                .movieDto(image.getMovie() == null ? null : fromMovieToMovieDto(image.getMovie()))
                .build();
    }

    static Image fromImageDtoToImage(ImageDto imageDto) {
        return imageDto == null ? null : Image.builder()
                .id(imageDto.getId())
                .poster(imageDto.getPoster())
                .movie(imageDto.getMovieDto() == null ? null : fromMovieDtoToMovie(imageDto.getMovieDto()))
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

    static SeatDto fromSeatToSeatDto(Seat seat) {
        return seat == null ? null : SeatDto.builder()
                .id(seat.getId())
                .columnNumber(seat.getColumnNumber())
                .rowNumber(seat.getRowNumber())
                .reservation(seat.getReservation() == null ? null : fromReservationToReservationDto(seat.getReservation()))
                .user(seat.getUser() == null ? null : fromUserToUserDto(seat.getUser()))
                //.repertoire(seat.getRepertoire() == null ? null : fromRepertoireToRepertoireDto(seat.getRepertoire()))
                .build();
    }


    static Seat fromSeatDtoToSeat(SeatDto seatDto) {
        return seatDto == null ? null : Seat.builder()
                .id(seatDto.getId())
                .columnNumber(seatDto.getColumnNumber())
                .rowNumber(seatDto.getRowNumber())
                //.repertoire(seatDto.getRepertoire() == null ? null : fromRepertoireDtoToRepertoire(seatDto.getRepertoire()))
                .user(seatDto.getUser() == null ? null : fromUserDtoToUser(seatDto.getUser()))
                .reservation(seatDto.getReservation() == null ? null : fromReservationDtoToReservation(seatDto.getReservation()))
                .build();
    }


    static RepertoireDto fromRepertoireToRepertoireDto(Repertoire repertoire) {
        return repertoire == null ? null : RepertoireDto.builder()
                .id(repertoire.getId())
                .time(repertoire.getTime())
                .date(repertoire.getDate())
                .movieId(repertoire.getId())
                //.movie(repertoire.getMovie() == null ? null : fromMovieToMovieDto(repertoire.getMovie()))
                // .seat(repertoire.getSeat() == null ? null : fromSeatToSeatDto(repertoire.getSeat()))
                .build();
    }

    static Repertoire fromRepertoireDtoToRepertoire(RepertoireDto repertoireDto) {
        return repertoireDto == null ? null : Repertoire.builder()
                .id(repertoireDto.getId())
                .time(repertoireDto.getTime())
                .date(repertoireDto.getDate())
           //     .movie(repertoireDto.)
                //.movieId(repertoireDto.getMovieId())
                //  .movie(repertoireDto.getMovie() == null ? null : fromMovieDtoToMovie(repertoireDto.getMovie()))
                //  .seat(repertoireDto.getSeat() == null ? null : fromSeatDtoToSeat(repertoireDto.getSeat()))
                .build();
    }


    static ReviewDto fromReviewToReviewDto(Review review) {
        return review == null ? null : ReviewDto.builder()
                .id(review.getId())
                .movieId(review.getMovie().getId())
                .userId(review.getUser().getId())
                .description(review.getDescription())
                //.userDto(review.getUser() == null ? null : fromUserToUserDto(review.getUser()))
                .build();
    }

    static Review fromReviewDtoToReview(ReviewDto reviewDto) {
        return reviewDto == null ? null : Review.builder()
                .id(reviewDto.getId())
                .description(reviewDto.getDescription())
                .build();

    }

}
