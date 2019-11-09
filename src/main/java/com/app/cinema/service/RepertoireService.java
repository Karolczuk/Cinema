package com.app.cinema.service;

import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Repertoire;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.RepertoireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RepertoireService {

    private final RepertoireRepository repertoireRepository;
    private final MovieRepository movieRepository;

    public RepertoireDto add(RepertoireDto repertoireDto) {

        if (repertoireDto == null) {
            throw new AppException("add repertoire exception - repertoire object is null");
        }

        Optional<Movie> optionalMovie = movieRepository.findById(repertoireDto.getMovieId());

        var repertoire = ModelMapper.fromRepertoireDtoToRepertoire(repertoireDto);
        if (optionalMovie.isPresent()) {
            repertoire.setMovie(optionalMovie.get());
        }
        else{
            throw  new AppException("Movie Id doesn't exist");
        }
        return ModelMapper.fromRepertoireToRepertoireDto(repertoireRepository.save(repertoire));
    }

    public List<RepertoireDto> findAll(Long movieId) {
        return repertoireRepository.findByMovieId(movieId)
                .stream()
                .map(ModelMapper::fromRepertoireToRepertoireDto)
                .collect(Collectors.toList());
    }

    public List<RepertoireDto> findByMovieIdAndDate(Long movieId, LocalDate date) {

        return repertoireRepository.findByMovieIdAndDateAndTimeAfter(movieId, date, LocalTime.now())
                .stream()
                .map(ModelMapper::fromRepertoireToRepertoireDto)
                .collect(Collectors.toList());
    }

    public RepertoireDto update(Long id, RepertoireDto repertoireDto) {

        if (id == null) {
            throw new AppException("update repertoire exception - id is null");
        }
        if (repertoireDto == null) {
            throw new AppException("update repertoire exception - repertoire object is null");
        }

        Repertoire repertoire = repertoireRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no repertoire with id " + id));

        Movie movie = movieRepository
                .findById(repertoireDto.getMovieId())
                .orElseThrow(() -> new AppException("no movie with id " + repertoireDto.getMovieId()));

        repertoire.setMovie(movie);

        repertoire.setDate(repertoireDto.getDate());

        return ModelMapper.fromRepertoireToRepertoireDto(repertoireRepository.save(repertoire));

    }

}
