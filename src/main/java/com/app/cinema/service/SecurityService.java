package com.app.cinema.service;

import com.app.cinema.dto.RefreshTokenData;
import com.app.cinema.dto.Tokens;
import com.app.cinema.dto.UserDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.User;
import com.app.cinema.repository.RoleRepository;
import com.app.cinema.repository.UserRepository;
import com.app.cinema.service.security.tokens.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public String registerUser(UserDto registrationUser) {
        if (registrationUser == null) {
            throw new AppException("registration user object is null!");
        }

        if (!Objects.equals(registrationUser.getPassword(), registrationUser.getPasswordConfirmation())) {
            throw new AppException("passwords are not correct!");
        }

//        if (registrationUser.getRoles() == null || registrationUser.getRoles().isEmpty()) {
//            throw new AppException("user without roles!");
//        }

        if ( userRepository.findByUsername(registrationUser.getUsername()).isPresent()) {
            throw new AppException("username " + registrationUser.getUsername() + " already exists!");
        }

//        var roles = registrationUser
//                .getRoles()
//                .stream()
//                .map(roleName -> roleRepository.findByName(roleName).orElseThrow(() -> new AppException("no role with name " + roleName)))
//                .collect(Collectors.toSet());

        var user = User.builder()
                .username(registrationUser.getUsername())
                .password(passwordEncoder.encode(registrationUser.getPassword()))
               // .roles(roles)
                .build();

        roleRepository.findByName("ROLE_USER").ifPresent(r->user.setRoles(Collections.singleton(r)));

        var insertedUser = userRepository.save(user);
        return "User " + insertedUser.getUsername() + " has been registered";
    }

    public Tokens generateRefreshToken(RefreshTokenData refreshTokenData) {

        if (refreshTokenData == null) {
            throw new AppException("refresh token object is null");
        }

        return TokenService.parseRefreshToken(refreshTokenData.getRefreshToken());
    }


//    public String saveUserWithToken( User user, String token ) {
//
//        if ( user == null ) {
//            throw new AppException("save user with token - user is null");
//        }
//
//        if ( token == null ) {
//            throw new AppException("save user with token - token is null");
//        }
//
//        VerificationToken verificationToken = verificationTokenRepository.save(VerificationToken.builder()
//                .user(user)
//                .token(token)
//                .expirationDateTime(LocalDateTime.now().plusMinutes(5))
//                .build());
//
//        return verificationToken.getToken();
//
//    }
//
//    public void activateUser( String token ) {
//
//        if ( token == null ) {
//            throw new AppException("token is null");
//        }
//
//        VerificationToken verificationToken = verificationTokenRepository
//                .findByToken(token)
//                .orElseThrow(() -> new AppException("no user for token " + token));
//
//
//        if ( verificationToken.getExpirationDateTime().isBefore(LocalDateTime.now()) ) {
//            throw new AppException("token has been expired");
//        }
//
//
//        User user = verificationToken.getUser();
//        user.setEnabled(true);
//
//        userRepository.save(user);
//
//    }
}
