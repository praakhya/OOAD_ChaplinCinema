package com.pes.chaplincinemabackend.services;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.common.exceptions.BadRequestException;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public Optional<Customer> findByUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        customer.setPassword(null);
        return Optional.of(customer);
    }
    public List<Customer> findByFirstname(String firstName) {
        List<Customer> users = customerRepository.findCustomersByFirstname(firstName);
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public List<Customer> findByLastname(String lastName) {
        List<Customer> users = customerRepository.findCustomersByLastname(lastName);
        users.forEach(u -> u.setPassword(null));
        return users;
    }
    public Optional<Customer> findByID(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new UsernameNotFoundException(id.toString()));
        return Optional.of(customer);
    }
    public List<Customer> findAll() {
        List<Customer> users = customerRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public Optional<Customer> create(Customer user) {
        if (customerRepository.findCustomerByUsername(user.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.USER_ALREADY_EXISTS.getReason(), user.getUsername()),
                    String.format(ExceptionMessage.USER_ALREADY_EXISTS.getError(), user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }
    public Optional<Customer> update(Customer user) {
        if (user.getId() == null) {
            throw new BadRequestException(ExceptionMessage.ID_DOESNOT_EXIT.getError(), ExceptionMessage.ID_DOESNOT_EXIT.getReason());
        }
        customerRepository.findById(user.getId()).orElseThrow(()-> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getReason(), user.getUsername()),
                String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(), user.getUsername())));
        return save(user);
    }
    public Optional<Customer> save(Customer customer) {
        customer.setGrantedAuthorities(Set.of(Role.CUSTOMER));
        customer.setCreatedAt(new Date());
        customer.setCreatedBy(customer.getUsername());
        customer.setUpdatedBy(customer.getUsername());
        return Optional.of(customerRepository.save(customer));
    }
    public List<Customer> findAllCustomers() {
        List<Customer> users = customerRepository.findAllCustomers();
        users.forEach(u -> u.setPassword(null));
        return users;
    }
    public Optional<Customer> deleteCustomer(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        customerRepository.deleteById(customer.getId());
        return Optional.of(customer);

    }

    public List<Customer> searchCustomerBySubstring(String phrase) {
        return customerRepository.searchCustomerBySubstring(phrase);
    }
}
