package com.pes.chaplincinemabackend.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.repositories.AdminRepository;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Admin> findOne(String username) {
        return adminRepository.findAdminByUsername(username);
    }
    public Optional<Admin> findOneByID(UUID id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        List<Admin> users = adminRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }


    public Optional<Admin> save(Admin user) {
        if (adminRepository.findAdminByUsername(user.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.USER_ALREADY_EXISTS.getReason(), user.getUsername()),
                    String.format(ExceptionMessage.USER_ALREADY_EXISTS.getError(), user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGrantedAuthorities(Set.of(Role.ADMIN));
        user.setCreatedAt(new Date());
        user.setCreatedBy(user.getUsername());
        user.setUpdatedBy(user.getUsername());
        return Optional.of(adminRepository.save(user));
    }

}
