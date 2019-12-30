//package com.app.cinema.controller;
//import com.app.cinema.dto.ReviewDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    @GetMapping("/{movieId}")
//    public List<ReviewDto> findReviewByMovie(@PathVariable Long movieId) {
//        return reviewService.findReviewByMovieId(movieId);
//    }
//
//    @GetMapping("/all/{page}/{size}")
//    public Page<ReviewDto> findAllReview(@PathVariable Integer page, @PathVariable Integer size) {
//        return reviewService.findAll(PageRequest.of(page, size));
//    }
//
//    @PostMapping
//    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
//        return reviewService.add(reviewDto);
//    }
//}
//
//
