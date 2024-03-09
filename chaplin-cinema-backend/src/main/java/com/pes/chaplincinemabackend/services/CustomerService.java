package com.pes.chaplincinemabackend.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.permissions.UserPermissions;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Customer> findOne(String username) {
        return customerRepository.findCustomersByUsername(username);
    }
    public Optional<Customer> findOneByID(UUID id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        List<Customer> users = customerRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public Optional<Customer> save(Customer user) {
        if (customerRepository.findCustomersByUsername(user.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.USER_ALREADY_EXISTS.getReason(), user.getUsername()),
                    String.format(ExceptionMessage.USER_ALREADY_EXISTS.getError(), user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGrantedAuthorities(Set.of(Role.CUSTOMER));
        user.setCreatedAt(new Date());
        user.setCreatedBy(user.getUsername());
        user.setUpdatedBy(user.getUsername());
        return Optional.of(customerRepository.save(user));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Customer> findAllCustomers() {
        List<Customer> users = customerRepository.findAllCustomers();
        users.forEach(u -> u.setPassword(null));
        return users;
    }
    @PreAuthorize("@userPermissions.authorizedForAdminAndCurrentCustomer(principal, #username)")
    public Optional<Customer> deleteCustomer(String username) {
        Customer customer = customerRepository.findCustomersByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        customerRepository.deleteById(customer.getId());
        return Optional.of(customer);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Customer> findCustomersByFirstName(String firstName) {
        List<Customer> users = customerRepository.findCustomersByFirstname(firstName);
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Customer> findCustomersByLastName(String lastName) {
        List<Customer> users = customerRepository.findCustomersByLastname(lastName);
        users.forEach(u -> u.setPassword(null));
        return users;
    }
}
