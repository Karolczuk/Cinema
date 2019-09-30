package com.app.cinema.controller;


import com.app.cinema.dto.UserDto;
import com.app.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto add(UserDto userDto) {
        return userService.add(userDto);
    }

}
