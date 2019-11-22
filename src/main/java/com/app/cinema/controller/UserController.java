package com.app.cinema.controller;


import com.app.cinema.dto.UserDto;
import com.app.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto add(UserDto userDto) {
        return userService.add(userDto);
    }

    @GetMapping
    public UserDto getUserData() {
        return userService.getCurrentUser();
    }

    @PutMapping("/{id}")  // cos tu nie działa
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.update(id,userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{username}{email}")
    public void updateCurrentUser(@PathVariable String username, @PathVariable String email){
        userService.updateCurrentUser(username,email);
    }
}
