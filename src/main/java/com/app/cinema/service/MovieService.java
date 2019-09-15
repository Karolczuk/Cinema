package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.validator.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDto> findAll() {

        return movieRepository
                .findAll()
                .stream()
                .map(ModelMapper::fromMovieToMovieDto)
                .collect(Collectors.toList());
    }

    public MovieDto findOne(Long id) {

        if (  id == null ) {
            throw new AppException("find one exception - id is null");
        }

        var team = movieRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no movie with id " + id));

        return ModelMapper.fromMovieToMovieDto(team);
    }

    public MovieDto add ( MovieDto movieDto ) {

        if ( movieDto == null ) {
            throw new AppException("add team exception - team object is null");
        }

        var movieValidator = new MovieValidator();
        var errors = movieValidator.validate( movieDto );
        if ( movieValidator.hasErrors() ) {
            throw new AppException(errors.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")));
        }

        var movie = ModelMapper.fromMovieDtoToMovie(movieDto);
        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public MovieDto update ( Long id, MovieDto movieDto ) {

        if ( id == null ) {
            throw new AppException("update movie exception - id is null");
        }

        if ( movieDto == null ) {
            throw new AppException("update movie exception - movie object is null");
        }

        var movieValidator = new MovieValidator();
        var errors = movieValidator.validate( movieDto );
        if ( movieValidator.hasErrors() ) {
            throw new AppException(errors.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")));
        }

        var movie = movieRepository
                .findById(id)
                .orElseThrow(() -> new AppException("update movie exception - no movie with id " + id));

        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public MovieDto deleteById( Long id ) {

        if ( id == null ) {
            throw new AppException("delete exception - id is null");
        }

        var team = movieRepository
                .findById(id)
                .orElseThrow(() -> new AppException("delete exception - no movie with id " + id));

        movieRepository
                .findAll()
                .stream()
                .forEach(movieRepository::save);

        movieRepository.delete(team);
        return ModelMapper.fromMovieToMovieDto(team);

    }

}
