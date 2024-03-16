package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
    @Query("{username: ?0}")
    Optional<Customer> findCustomerByUsername(String username);

    @Query("{$and:[{firstName: ?0},{grantedAuthorities: 'CUSTOMER'}]}")
    List<Customer> findCustomersByFirstname(String firstName);
    @Query("{$and:[{lastName: ?0},{grantedAuthorities: 'CUSTOMER'}]}")
    List<Customer> findCustomersByLastname(String firstName);
    @Query("{grantedAuthorities: {$elemMatch : {$eq : 'CUSTOMER'}}}")
    List<Customer> findAllCustomers();
}
