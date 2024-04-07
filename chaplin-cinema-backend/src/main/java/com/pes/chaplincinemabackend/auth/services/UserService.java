package com.pes.chaplincinemabackend.auth.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.auth.repositiories.UserRepository;
import com.pes.chaplincinemabackend.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findOne(String username) {
        return userRepository.findUsersByUsername(username);
    }
    public Optional<User> findOneByID(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public Optional<User> save(User user) {
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
    public Optional<User> delete(String username) {
        User user = userRepository.findUsersByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        userRepository.deleteById(user.getId());
        return Optional.of(user);
    }
    public Optional<User> delete(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        userRepository.deleteById(user.getId());
        return Optional.of(user);
    }

    public void makeAdmin(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.getGrantedAuthorities().add(Role.ADMIN);
        userRepository.save(user);
    }
    public void removeAdmin(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.getGrantedAuthorities().remove(Role.ADMIN);
        userRepository.save(user);
    }
    public List<User> searchUserBySubstr(String phrase) {
        return userRepository.searchUserBySubstring(phrase);
    }
}