package com.app.cinema.service;

import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.dto.SeatDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Repertoire;
import com.app.cinema.model.Seat;
import com.app.cinema.model.User;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.RepertoireRepository;
import com.app.cinema.repository.SeatRepository;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatService {

    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;
    private final RepertoireRepository repertoireRepository;
    private  final UserRepository userRepository;

     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    public SeatDto add(SeatDto seatDto) {

        if (seatDto == null) {
            throw new AppException("add seat exception - seat object is null");
        }

//         Optional<Repertoire> optionalRepertoire = repertoireRepository.findByMovieIdAndDateAndTime(seatDto.getMovieId(), seatDto.getDate(), seatDto.getTime());
        //        List<Reservation> optionalReservations = reservationRepository.findByTimeAndDateAndMovieId(seatDto.getTime(), seatDto.getDate(), seatDto.getMovieId());
//        var seat = ModelMapper.fromSeatDtoToSeat(seatDto);
        Optional<Repertoire> byId = repertoireRepository.findById(seatDto.getRepertoireDto().getId());
        boolean present = byId.isPresent();

//        if (byId.isPresent()) {
//            byId.get();
//            Optional<Seat> optionalSeat = seatRepository.findByRepertoireDateAndRepertoireTimeAndColumnNumberAndRowNumberAndRepertoireMovieId(LocalDate.parse(seatDto.getRepertoireDto().getDate(), formatter), seatDto.getRepertoireDto().getTime(), seatDto.getColumnNumber(), seatDto.getRowNumber(), seatDto.getRepertoireDto().getMovieId());


        Optional<Seat> optionalSeat = seatRepository.findByRepertoireDateAndRepertoireTimeAndColumnNumberAndRowNumberAndRepertoireMovieId(LocalDate.parse(seatDto.getRepertoireDto().getDate(),formatter), seatDto.getRepertoireDto().getTime(), seatDto.getColumnNumber(), seatDto.getRowNumber(), seatDto.getRepertoireDto().getMovieId());
            if (optionalSeat.isPresent()) {
                throw new AppException("Someone reserved this seat");
            }
            var seat = ModelMapper.fromSeatDtoToSeat(seatDto);

            Optional<User> optionalUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());// pobieamy nazwe uzytkownika z kontejstu security, kontext security - kontekst w ktorym przetrzymuje akualnego uzytkownika ktory przedtswił sie tokenem
            if (!optionalUser.isPresent()) {
                throw new AppException("User doe not exist");
            }
            seat.setUser(optionalUser.get());
            return ModelMapper.fromSeatToSeatDto(seatRepository.save(seat));
//        }
//        throw new AppException("Repertoire doesn' exist");
    }

    public List<SeatDto> findAll(LocalDate date, String time, Long movieId) {
        return seatRepository.findByRepertoireDateAndRepertoireTimeAndRepertoireMovieId(date, LocalTime.parse(time), movieId)
                .stream()
                .map(ModelMapper::fromSeatToSeatDto)
                .collect(Collectors.toList());
    }
}


