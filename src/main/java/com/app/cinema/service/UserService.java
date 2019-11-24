package com.app.cinema.service;

import com.app.cinema.dto.UserDto;
import com.app.cinema.dto.VideoDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.User;
import com.app.cinema.model.Video;
import com.app.cinema.repository.RoleRepository;
import com.app.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



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

        if (userDto.getPassword() == null || userDto.getPasswordConfirmation() == null) {
            throw new AppException(" - id is null");
        }

        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordConfirmation())) {
            throw new AppException("passwords are not correct!");
        }
        return userRepository
                .findById(id)
                .map(u -> {

                    u.setUsername(userDto.getUsername() == null ? u.getUsername() : userDto.getUsername());
                    u.setEmail(userDto.getEmail() == null ? u.getEmail() : userDto.getEmail());
                    u.setPassword(userDto.getPassword() == null ? u.getPassword() : passwordEncoder.encode(userDto.getPassword()));
                    roleRepository.findByName("ROLE_USER").ifPresent(r->u.setRoles(new HashSet<>(Set.of(r))));
                    return ModelMapper.fromUserToUserDto(userRepository.save(u));
                }).orElseThrow(() -> new AppException("User service - update - cannot update user with id " + userDto.getId()));
    }


    public UserDto updateCurrentUser(String username, String email) {

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new AppException("User is not logged"));
        user.setUsername(username);
        user.setEmail(email);


        return ModelMapper.fromUserToUserDto(userRepository.save(user));
    }


    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDto> users = userPage.getContent()
                .stream()
                .map(ModelMapper::fromUserToUserDto)
                .collect(Collectors.toList());
        return new PageImpl<>(users, userPage.getPageable(), userPage.getTotalElements());
    }
}

