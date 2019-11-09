package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Before
    public void init() {
        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.of(Movie
                .builder()
                .id(1L)
                .build()));

        Mockito.when(movieRepository.findById(3L)).thenReturn(Optional.of(Movie
                .builder()
                .id(3L)
                .build()));

        Mockito.when(movieRepository.save(Movie.builder().id(2L).title("cats").description("new movie").price(new BigDecimal(23)).build())).thenReturn(Movie.builder().id(2L).title("cats").description("new movie").price(new BigDecimal(23)).build());

        Mockito.when(movieRepository.save(Movie.builder().id(1L).title("cats").description("new movie").price(new BigDecimal(23)).build())).thenReturn(Movie.builder().id(1L).title("cats").description("new movie").price(new BigDecimal(23)).build());

        Mockito.when(movieRepository.save(Movie.builder().id(3L).title("elephant").description("new movie").price(new BigDecimal(23)).build())).thenReturn(Movie.builder().id(1L).title("elephant").description("new movie").price(new BigDecimal(23)).build());


    }

    @Test
    public void shouldReturnMovieDtoById() {
        MovieDto result = movieService.findOne(1L);
        assertThat(result).isEqualTo(MovieDto.builder().id(1L).build());
    }

    @Test
    public void shouldReturnMovieDto() {
        MovieDto result = movieService.add(MovieDto.builder().id(2L).title("cats").description("new movie").price(new BigDecimal(23)).build());
        assertThat(result).isEqualTo(MovieDto.builder().id(2L).title("cats").description("new movie").price(new BigDecimal(23)).build());
    }

    @Test(expected = AppException.class)
    public void shouldReturnException() {
        movieService.deleteById(null);
    }

    @Test()
    public void shouldReturnExceptionWhenIdNull() {
        Throwable throwable = catchThrowable(() -> movieService.deleteById(null));
        assertThat(throwable).isInstanceOf(AppException.class).hasMessage("delete exception - id is null");
    }

    @Test()
    public void shoulnMovieDto() {
        movieService.deleteById(1L);
        Throwable throwable = catchThrowable(() -> movieService.findOne(10L));
        assertThat(throwable).isInstanceOf(AppException.class);
    }


}


// w jedenym tesdcie nie mofe uruchamiac 2 metod z serwosu