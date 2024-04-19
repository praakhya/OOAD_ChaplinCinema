package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends MongoRepository<Admin, UUID> {
    @Query("{username: ?0}")
    Optional<Admin> findAdminByUsername(String username);
}
