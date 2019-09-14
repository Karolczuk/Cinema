package com.app.cinema.dto;//package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String password;
    private String passwordConfirmation;

}
