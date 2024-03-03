package com.pes.chaplincinemabackend.auth.services;

import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.auth.exceptions.InvalidPasswordException;
import com.pes.chaplincinemabackend.auth.pojos.AuthToken;
import com.pes.chaplincinemabackend.auth.pojos.Login;
import com.pes.chaplincinemabackend.auth.repositiories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public Optional<com.pes.chaplincinemabackend.auth.pojos.User> auth(Login login) {
        User userEntity
                = userRepository
                .findUsersByUsername(login.username())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", login.username())));
        if (passwordEncoder.matches(login.password(), userEntity.getPassword())) {
            AuthToken authToken = generateTokens(userEntity);
            com.pes.chaplincinemabackend.auth.pojos.User user = new com.pes.chaplincinemabackend.auth.pojos.User(
                    userEntity.getUsername(),
                    userEntity.getProfilePictureURL(),
                    userEntity.getId(),
                    userEntity.getGrantedAuthorities(),
                    authToken
            );
            return Optional.of(user);
        }
        throw new InvalidPasswordException(String.format(ExceptionMessage.INVALID_PASSWORD.getReason(), login.username()),
                String.format(ExceptionMessage.INVALID_PASSWORD.getError(), userEntity.getUsername()));
    }

    public Optional<AuthToken> refresh(User user) {
        return Optional.of(generateTokens(user));
    }
    private AuthToken generateTokens(User user) {
        String authToken = jwtService.generateAuthToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthToken(user.getUsername(), user.getProfilePictureURL(), authToken, refreshToken, user.getGrantedAuthorities());
    }
}
