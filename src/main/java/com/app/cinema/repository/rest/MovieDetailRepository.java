package com.app.cinema.repository.rest;

import com.app.cinema.model.Image;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Video;
import com.app.cinema.model.rest.*;
import com.app.cinema.repository.ImageRepository;
import com.app.cinema.repository.MovieRepository;
import com.app.cinema.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

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

    @Value("api.key")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 600000)
    public void getMovieList() {


        for (int i = 100; i < 150; i++) {
            try {


                MovieDetails forObject = restTemplate.getForObject("http://api.themoviedb.org/3/movie/" + i + "?api_key=16209cb29ac8fa4b449f259e5b4263ce&language=en-US",
                        MovieDetails.class);
                System.out.println(forObject);


                Optional<Movie> byTitle = movieRepository.findByTitle(forObject.getTitle());
                if (!byTitle.isPresent()) {


                    VideoRest forObject1 = restTemplate.getForObject("http://api.themoviedb.org/3/movie/" + i + "/videos?api_key=16209cb29ac8fa4b449f259e5b4263ce&language=en-US",
                            VideoRest.class);

                    System.out.println(forObject);
                    Movie movie = Movie.builder()
                            .description(forObject.getOverview())
                            .genre(forObject.getGenres()
                                    .stream()
                                    .map(Genre::getName)
                                    .collect(Collectors.toList()))
                            .title(forObject.getTitle())
                            .releaseDate(forObject.getRelease_date())
                            .duration(forObject.getRuntime())
                            .adult(forObject.getAdult())
                            .build();

                    Movie movieDb = movieRepository.save(movie);


                    List<Video> videos = forObject1.getResults().stream().map(v -> Video
                            .builder()
                            .keyHash("https://www.youtube.com/watch?v=" + v.getKey())
                            .name(v.getName())
                            .site(v.getSite())
                            //    .movie(movieDb)
                            .build())
                            .collect(Collectors.toList());
                    videoRepository.saveAll(videos);

                    Image images = Image.builder().poster("https://image.tmdb.org/t/p/w200" + forObject.getPoster_path()).movie(movieDb).build();


                    imageRepository.save(images);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }

}
