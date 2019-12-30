//package com.app.cinema.service;
//
//import com.app.cinema.dto.ReviewDto;
//import com.app.cinema.exceptions.AppException;
//import com.app.cinema.model.Movie;
//import com.app.cinema.model.User;
//import com.app.cinema.repository.MovieRepository;
//import com.app.cinema.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ReviewService {
//
//    private final ReviewRepository reviewRepository;
//    private final UserRepository userRepository;
//    private final MovieRepository movieRepository;
//
//    public Page<ReviewDto> findAll(Pageable pageable) {
//        Page<Review> reviewPage = reviewRepository.findAll(pageable);
//        List<ReviewDto> reviews = reviewPage.getContent()
//                .stream()
//                .map(ModelMapper::fromReviewToReviewDto)
//                .collect(Collectors.toList());
//        return new PageImpl<>(reviews, reviewPage.getPageable(), reviewPage.getTotalElements());
//    }
//
//    public List<ReviewDto> findReviewByMovieId(Long movieId) {
//        return reviewRepository.findByMovieId(movieId)
//                .stream()
//                .map(ModelMapper::fromReviewToReviewDto)
//                .collect(Collectors.toList());
//    }
//
//    public ReviewDto add(ReviewDto reviewDto) {
//
//        if (reviewDto == null) {
//            throw new AppException("add review exception - review object is null");
//        }
//
//        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
//        Optional<Movie> optionalMovie = movieRepository.findById(reviewDto.getMovieId());
//        var review = ModelMapper.fromReviewDtoToReview(reviewDto);
//
//        if (optionalUser.isPresent() && optionalMovie.isPresent()){
//            review.setMovie(optionalMovie.get());
//            review.setUser(optionalUser.get());
//        }
//        return ModelMapper.fromReviewToReviewDto(reviewRepository.save(review));
//
//    }
//
//
//}
