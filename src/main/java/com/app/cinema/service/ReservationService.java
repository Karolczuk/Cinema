package com.app.cinema.service;

import com.app.cinema.dto.ReservationDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Reservation;
import com.app.cinema.repository.ReservationRepository;
import com.app.cinema.validator.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

private final ReservationRepository reservationRepository;

    public ReservationDto add (ReservationDto reservationDto ) {

        if ( reservationDto == null ) {
            throw new AppException("add reservationDto exception - team object is null");
        }

//        var movieValidator = new MovieValidator();
//        var errors = movieValidator.validate( bookingDto );
//        if ( movieValidator.hasErrors() ) {
//            throw new AppException(errors.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")));
//        }

        var reservation = ModelMapper.fromReservationDtoToReservation(reservationDto);
        return ModelMapper.fromReservationToReservationDto(reservationRepository.save(reservation));

    }
}
