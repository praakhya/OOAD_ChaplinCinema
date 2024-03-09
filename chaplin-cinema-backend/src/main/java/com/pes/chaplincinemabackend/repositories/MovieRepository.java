package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends MongoRepository<Movie, UUID> {
    @Query("{title: ?0}")
    List<Movie> findByName(String name);
}
