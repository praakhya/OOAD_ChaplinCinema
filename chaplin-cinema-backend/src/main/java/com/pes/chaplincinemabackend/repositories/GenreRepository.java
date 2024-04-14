package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends MongoRepository<Genre, UUID> {
    @Query("{genre_name: ?0}")
    Optional<Genre> findGenreByName(String name);
    @Query("{}")
    List<Genre> findGenres();
}
