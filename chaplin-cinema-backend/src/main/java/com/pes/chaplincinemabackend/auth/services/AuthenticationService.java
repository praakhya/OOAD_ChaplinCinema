package com.pes.chaplincinemabackend.auth.services;

import com.pes.chaplincinemabackend.auth.entities.UserEntity;
import com.pes.chaplincinemabackend.auth.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.auth.exceptions.InvalidPasswordException;
import com.pes.chaplincinemabackend.auth.pojos.AuthToken;
import com.pes.chaplincinemabackend.auth.pojos.Login;
import com.pes.chaplincinemabackend.auth.pojos.User;
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

    public Optional<User> auth(Login login) {
        UserEntity userEntity
                = userRepository
                .findUsersByUsername(login.username())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", login.username())));
        if (passwordEncoder.matches(login.password(), userEntity.getPassword())) {
            AuthToken authToken = generateTokens(userEntity);
            User user = new User(
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

    public Optional<AuthToken> refresh(UserEntity user) {
        return Optional.of(generateTokens(user));
    }
    private AuthToken generateTokens(UserEntity user) {
        String authToken = jwtService.generateAuthToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthToken(user.getUsername(), user.getProfilePictureURL(), authToken, refreshToken, user.getGrantedAuthorities());
    }
}
