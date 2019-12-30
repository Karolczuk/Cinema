package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Genre;
import com.app.cinema.model.Image;
import com.app.cinema.model.Movie;
import com.app.cinema.repository.GenreRepository;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.validator.MovieValidator;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Temporal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public Page<MovieDto> findAll(Pageable pageable, String movieTitle) {
        Page<Movie> moviePage = null;
        if (movieTitle!=null) {
            moviePage = movieRepository.findByTitleContaining(movieTitle, pageable);
        }
        else{
            moviePage = movieRepository.findAll(pageable);

        }
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
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        List<Genre> byNameIn = genreRepository.findByNameIn(movieDto.getGenres());
        movie.setGenres(byNameIn);
        // movie.setGenres(movieDto.getGenres().stream().map(g -> Genre.builder().name(g).build()).collect(Collectors.toList()));

        // movie.setDuration(movieDto.getDuration());
        //   movie.setImages(movieDto.getImageDtos().stream().map(ModelMapper::fromImageDtoToImage).collect(Collectors.toList()));
        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public MovieDto update(Long id, MovieDto movieDto) {

        if (id == null) {
            throw new AppException("update movie exception - id is null");
        }
        if (movieDto == null) {
            throw new AppException("update movie exception - movie object is null");
        }

        var movie = movieRepository
                .findById(id)
                .orElseThrow(() -> new AppException("update movie exception - no movie with id " + id));

        movie.setDescription(movieDto.getDescription());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        movie.setReleaseDate(movieDto.getReleaseDate());

        return ModelMapper.fromMovieToMovieDto(movieRepository.save(movie));

    }

    public void deleteById(Long id) {

        if (id == null) {
            throw new AppException("delete exception - id is null");
        }

        movieRepository.deleteById(id);

    }


//    public Page<MovieDto> findMovie(String title, Pageable pageable) {
//        List<Movie> collect = movieRepository.findByTitleContaining(title,pageable).stream()
//                .collect(Collectors.toList());
//        return new PageImpl<>(collect, moviePage.getPageable(), moviePage.getTotalElements());

//}
}
