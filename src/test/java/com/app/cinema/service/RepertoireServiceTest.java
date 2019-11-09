package com.app.cinema.service;

import com.app.cinema.dto.RepertoireDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Repertoire;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.RepertoireRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)

public class RepertoireServiceTest {

    @Mock
    private RepertoireRepository repertoireRepository;

    @InjectMocks
    private RepertoireService repertoireService;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Before
    public void init() {
        Mockito.when(repertoireRepository.findById(1L)).thenReturn(Optional.of(Repertoire
                .builder()
                .id(1L)
                .build()));

        Mockito.when(movieRepository.findById(3L)).thenReturn(Optional.of(Movie
                .builder()
                .id(3L)
                .build()));

        Mockito.when(movieRepository.save(Movie.builder().id(3L).title("elephant").description("new movie").price(new BigDecimal(23)).build())).thenReturn(Movie.builder().id(1L).title("elephant").description("new movie").price(new BigDecimal(23)).build());

        Mockito.when(repertoireRepository.save(Repertoire.builder().id(1L).date(LocalDate.of(1992, 3, 2)).time(LocalTime.of(12, 12)).movie(Movie.builder().id(6L).title("elephant").description("new movie").build())
                .build())).thenReturn(Repertoire.builder().id(1L).date(LocalDate.of(1992, 3, 2)).time(LocalTime.of(12, 12)).movie(Movie.builder().id(6L).title("elephant").description("new movie").build())
                .build());

        Mockito.when(movieRepository.findById(5L)).thenReturn(Optional.empty()); // nie moze sie powtarzac

        Mockito.when(movieRepository.findById(6L)).thenReturn(Optional.of(Movie.builder().id(6L).title("elephant").description("new movie").build())); // nie moze sie powtarzac


        Mockito.when(repertoireRepository.findByMovieIdAndDateAndTimeAfter(7L, LocalDate.of(1990, 12, 21), LocalTime.of(12, 21))).thenReturn(
                Collections.singletonList(Repertoire.builder().movie(Movie.builder().id(7L).build()).date(LocalDate.of(1990, 12, 21)).time(LocalTime.of(12, 21)).build()
                )
        );
    }

    @Test
    public void shouldReturnRepertoireDto() {
        Throwable throwable = catchThrowable(() -> repertoireService.add(RepertoireDto.builder().id(1L).date(LocalDate.of(1992, 3, 2)).time(LocalTime.of(12, 12))
                .movieId(5L)
                .build()));
        assertThat(throwable).isInstanceOf(AppException.class);
    }

    @Test
    public void shouldReturnExceptionWhenMovieIdNotexist() {
        RepertoireDto result = repertoireService.add(RepertoireDto.builder().id(1L).date(LocalDate.of(1992, 3, 2)).time(LocalTime.of(12, 12))
                .movieId(6L)
                .build());

        assertThat(result).isEqualTo(RepertoireDto.builder().id(1L).date(LocalDate.of(1992, 3, 2)).time(LocalTime.of(12, 12)).movieId(6L).build());
    }

    @Test
    public void shouldReturnExceptionWhenRepertoireIdNull() {
        Throwable throwable = catchThrowable(() -> repertoireService.add(null));
        assertThat(throwable).isInstanceOf(AppException.class);
    }
}
