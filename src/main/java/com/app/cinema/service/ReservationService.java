package com.app.cinema.service;

import com.app.cinema.dto.ReservationDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Reservation;
import com.app.cinema.model.User;
import com.app.cinema.repository.ReservationRepository;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationDto add(ReservationDto reservationDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (reservationDto == null || !optionalUser.isPresent()) {
            throw new AppException("add reservationDto exception - reservation object is null or user is null");
        }

        var reservation = ModelMapper.fromReservationDtoToReservation(reservationDto);
        reservation.setUser(optionalUser.get());
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
