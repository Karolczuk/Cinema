package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.ReservationDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Reservation;
import com.app.cinema.repository.ReservationRepository;
import com.app.cinema.validator.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        var reservation = ModelMapper.fromReservationDtoToReservation(reservationDto);
        return ModelMapper.fromReservationToReservationDto(reservationRepository.save(reservation));

    }


    public Page<ReservationDto> findAll(Pageable pageable) {
        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
        List<ReservationDto> reservation = reservationPage.getContent()
                .stream()
                .map(ModelMapper::fromReservationToReservationDto)
                .collect(Collectors.toList());
        return new PageImpl<>(reservation, reservationPage.getPageable(), reservationPage.getTotalElements());
    }
}
