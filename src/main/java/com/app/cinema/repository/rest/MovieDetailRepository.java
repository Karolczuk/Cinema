package com.app.cinema.repository.rest;

import com.app.cinema.model.*;
import com.app.cinema.model.Genre;
import com.app.cinema.model.rest.*;
import com.app.cinema.repository.GenreRepository;
import com.app.cinema.repository.ImageRepository;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class MovieDetailRepository {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Value("api.key")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 600000)

    public void downloadMovieData() {
        if (genreRepository.count() == 0) {

            GenreRest genres = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list?api_key=16209cb29ac8fa4b449f259e5b4263ce&language=en-US", GenreRest.class);
            List<Genre> genreList = genres.getGenres().stream()
                    .map(g -> Genre.builder().name(g.getName()).id(g.getId()).build())
                    .collect(Collectors.toList());
            genreRepository.saveAll(genreList);
        }

        for (int i = 1; i < 500; i++) {
            try {
                Discover discover = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=16209cb29ac8fa4b449f259e5b4263ce&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=true&page=" + i, Discover.class);
                discover.getResults().forEach(d -> saveMovieDetails(d));
                Thread.sleep(200);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private void saveMovieDetails(MovieDetails movieDetails) {

        Optional<Movie> byTitle = movieRepository.findByTitle(movieDetails.getTitle());
        if (!byTitle.isPresent()) {
            VideoRest forObject1 = restTemplate.getForObject("http://api.themoviedb.org/3/movie/" + movieDetails.getId() + "/videos?api_key=16209cb29ac8fa4b449f259e5b4263ce&language=en-US",
                    VideoRest.class);

            Movie movie = Movie.builder()
                    .description(movieDetails.getOverview())
                    .genres(genreRepository.findByIdIn(movieDetails.getGenre_ids()))
                    .title(movieDetails.getTitle())
                    .releaseDate(movieDetails.getRelease_date())
                    .duration(movieDetails.getRuntime())
                    .adult(movieDetails.getAdult())
                    .build();

            Movie movieDb = movieRepository.save(movie);

            List<Video> videos = forObject1.getResults().stream().map(v -> Video
                    .builder()
                    .keyHash("https://www.youtube.com/watch?v=" + v.getKey())
                    .name(v.getName())
                    .site(v.getSite())
                    .movie(movieDb)
                    .build())
                    .collect(Collectors.toList());
            videoRepository.saveAll(videos);

            Image images = Image.builder().poster("https://image.tmdb.org/t/p/w200" + movieDetails.getPoster_path()).movie(movieDb).build();
            imageRepository.save(images);
        }

    }


}
