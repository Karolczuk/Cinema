package com.app.cinema.controller;

import com.app.cinema.dto.Info;
import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.UserDto;
import com.app.cinema.model.User;
import com.app.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController{

    private final UserService userService;
        @PostMapping
        public ResponseEntity<Info<UserDto>> add(RequestEntity<UserDto> request) {

            HttpHeaders headers = request.getHeaders();
            System.out.println("--------------------------------");
            System.out.println(headers);
            UserDto insertedMovie = userService.add(request.getBody());

            return new ResponseEntity<>(
                    Info.<UserDto>builder().data(insertedMovie).build(),
                    HttpStatus.CREATED);
        }

}
