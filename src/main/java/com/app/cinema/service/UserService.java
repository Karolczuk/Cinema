package com.app.cinema.service;

import com.app.cinema.dto.ReservationDto;
import com.app.cinema.dto.UserDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Role;
import com.app.cinema.model.User;
import com.app.cinema.repository.ReservationRepository;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto add (UserDto userDto ) {

        if (userDto == null) {
            throw new AppException("user is null");
        }
        User user = ModelMapper.fromUserDtoToUser(userDto);
        System.out.println("-------------------- 2 -------------------------");
        System.out.println(user);
        System.out.println("-------------------- 2 -------------------------");

      //  user.setRole(Role.ROLE_USER);
       // user.setPassword(passwordEncoder.encode(user.getPassword()));

        return ModelMapper.fromUserToUserDto(userRepository.save(user));
    }

}
