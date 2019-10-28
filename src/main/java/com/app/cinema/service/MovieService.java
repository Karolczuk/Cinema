package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.validator.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Temporal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

        private final MovieRepository movieRepository;

    public Page<MovieDto> findAll(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<MovieDto> movies = moviePage.getContent()
                .stream()
                .map(ModelMapper::fromMovieToMovieDto)
                .collect(Collectors.toList());
        return new PageImpl<>(movies, moviePage.getPageable(), moviePage.getTotalElements());
    }


    public MovieDto findOne(Long id) {

        if (id == null) {
            throw new AppException("find one exception - id is null");
        }

        var movie = movieRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no movie with id " + id));

        return ModelMapper.fromMovieToMovieDto(movie);
    }

    public MovieDto add(MovieDto movieDto) {

        if (movieDto == null) {
            throw new AppException("add movie exception - movie object is null");
        }

//        var movieValidator = new MovieValidator();
//        var errors = movieValidator.validate(movieDto);
//        if (movieValidator.hasErrors()) {
//            throw new AppException(errors.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")));
//        }

        var movie = ModelMapper.fromMovieDtoToMovie(movieDto);
        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public MovieDto update( MovieDto movieDto) {



        if (movieDto == null) {
            throw new AppException("update movie exception - movie object is null");
        }

//        var movieValidator = new MovieValidator();
//        var errors = movieValidator.validate(movieDto);
//        if (movieValidator.hasErrors()) {
//            throw new AppException(errors.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")));
//        }

        var movie = movieRepository
                .findById(movieDto.getId())
                .orElseThrow(() -> new AppException("update movie exception - no movie with id " + movieDto.getId()));
 //
        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public void deleteById(Long id) {

        if (id == null) {
            throw new AppException("delete exception - id is null");
        }

        movieRepository.deleteById(id);

    }


}
