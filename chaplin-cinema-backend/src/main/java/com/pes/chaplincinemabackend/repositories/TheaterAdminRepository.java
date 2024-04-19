package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.TheaterAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TheaterAdminRepository extends MongoRepository<TheaterAdmin, UUID> {

}
