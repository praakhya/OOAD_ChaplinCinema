package com.pes.chaplincinemabackend.auth.repositiories;

import com.pes.chaplincinemabackend.auth.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    @Query("{username:?0}")
    Optional<User> findUsersByUsername(String username);
    @Query("{username:{$regex:/.*?0.*/, $options: 'i'}}")
    List<User> searchUserBySubstring(String substring);
}