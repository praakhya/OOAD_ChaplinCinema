package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
    @Query("{username: ?0}")
    Optional<Customer> findCustomerByUsername(String username);
    @Query("{$and:[{username:{$regex:/.*?0.*/, $options: 'i'}},{grantedAuthorities: 'CUSTOMER'}]}")
    List<Customer> searchCustomerBySubstring(String substring);

    @Query("{$and:[{firstName: ?0},{grantedAuthorities: 'CUSTOMER'}]}")
    List<Customer> findCustomersByFirstname(String firstName);
    @Query("{$and:[{lastName: ?0},{grantedAuthorities: 'CUSTOMER'}]}")
    List<Customer> findCustomersByLastname(String firstName);
    @Query("{grantedAuthorities: {$elemMatch : {$eq : 'CUSTOMER'}}}")
    List<Customer> findAllCustomers();
}
