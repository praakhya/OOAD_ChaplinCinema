package com.pes.chaplincinemabackend.auth.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.entities.UserEntity;
import com.pes.chaplincinemabackend.auth.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.auth.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.auth.repositiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findOne(String username) {
        return userRepository.findUsersByUsername(username);
    }
    public Optional<UserEntity> findOneByID(UUID id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public Optional<UserEntity> save(UserEntity user) {
        if (userRepository.findUsersByUsername(user.getUsername()).isPresent()) {

            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.USER_ALREADY_EXISTS.getReason(), user.getUsername()),
                    String.format(ExceptionMessage.USER_ALREADY_EXISTS.getError(), user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGrantedAuthorities(Set.of(Role.USER));
        user.setCreatedAt(new Date());
        user.setCreatedBy(user.getUsername());
        user.setUpdatedBy(user.getUsername());
        return Optional.of(userRepository.save(user));
    }

}