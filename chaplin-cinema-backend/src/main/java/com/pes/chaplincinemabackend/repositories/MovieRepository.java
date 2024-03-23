package com.pes.chaplincinemabackend.repositories;

import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    @Query("{title: ?0}")
    List<Movie> findByName(String name);
    @Query("{title:{$regex:/.*?0.*/, $options: 'i'}}")
    Page<Movie> searchByMoviePhrase(String phrase, PageRequest pageRequest);
}
