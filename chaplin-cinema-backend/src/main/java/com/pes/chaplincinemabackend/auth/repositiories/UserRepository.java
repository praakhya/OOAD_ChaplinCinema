package com.pes.chaplincinemabackend.auth.repositiories;

import com.pes.chaplincinemabackend.auth.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<UserEntity, UUID> {
    @Query("{username:?0}")
    Optional<UserEntity> findUsersByUsername(String username);
}