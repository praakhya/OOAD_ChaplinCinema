package com.pes.chaplincinemabackend.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.entities.Admin;
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

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }
    public Optional<Admin> findByID(UUID id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        List<Admin> users = adminRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }
    public Optional<Admin> update(Admin admin) {
        adminRepository.findAdminByUsername(admin.getUsername())
                .orElseThrow(() -> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getReason(), admin.getUsername()),
                String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(), admin.getUsername())));
        return save(admin);
    }

    public Optional<Admin> save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setGrantedAuthorities(Set.of(Role.ADMIN));
        admin.setCreatedAt(new Date());
        admin.setCreatedBy(admin.getUsername());
        admin.setUpdatedBy(admin.getUsername());
        return Optional.of(adminRepository.save(admin));
    }
    public Optional<Admin> create(Admin admin) {
        if (adminRepository.findAdminByUsername(admin.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.USER_ALREADY_EXISTS.getReason(), admin.getUsername()),
                    String.format(ExceptionMessage.USER_ALREADY_EXISTS.getError(), admin.getUsername()));
        }
        return save(admin);
    }

    public Optional<Admin> delete(String username) {
        Admin admin = adminRepository.findAdminByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        adminRepository.deleteById(admin.getId());
        return Optional.of(admin);
    }

}
