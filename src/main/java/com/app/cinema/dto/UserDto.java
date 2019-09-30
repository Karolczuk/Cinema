package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String password;
    private String passwordConfirmation;
    private SeansDto seansDto;
    private CardDto loyaltyCardDto;
    private ReviewDto reviewDto;

    private Set<String> roles;
}
