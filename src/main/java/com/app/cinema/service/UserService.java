package com.app.cinema.service;

import com.app.cinema.dto.UserDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.User;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto add(UserDto userDto) {

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

    public UserDto getCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .map(ModelMapper::fromUserToUserDto).orElseThrow(() -> new EntityNotFoundException("User not logged"));
    }


    public void delete(Long id) {

        if (id == null) {
            throw new AppException("User  id is null");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new AppException("User service - delete - no user with id " + id));
        userRepository.deleteById(id);
    }

    public UserDto update(Long id, UserDto userDto) {

        if (id == null) {
            throw new AppException("update userDto exception - id is null");
        }
        return userRepository
                .findById(id)
                .map(u -> {

                    u.setUsername(userDto.getUsername() == null ? u.getUsername() : userDto.getUsername());
                    u.setEmail(userDto.getEmail() == null ? u.getEmail() : userDto.getEmail());

                    return ModelMapper.fromUserToUserDto(userRepository.save(u));
                }).orElseThrow(() -> new AppException("User service - update - cannot update user with id " + userDto.getId()));
    }


    public UserDto updateCurrentUser(String username, String email) {

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new AppException("User is not logged"));
        user.setUsername(username);
        user.setEmail(email);

        return ModelMapper.fromUserToUserDto(userRepository.save(user));
    }
}

