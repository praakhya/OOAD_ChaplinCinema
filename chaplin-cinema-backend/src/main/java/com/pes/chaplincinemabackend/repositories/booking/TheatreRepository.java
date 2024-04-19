package com.pes.chaplincinemabackend.repositories.booking;

import com.pes.chaplincinemabackend.entities.booking.Theatre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends MongoRepository<Theatre, ObjectId> {
    public Theatre findTheatreByTheatreId(String theatreId);
    Optional<Theatre> findByName(String name);

    Theatre getTheatreBy_id(String _id);
    Theatre findByShowsContaining(String showId);


    Optional<Theatre> findById(String _id);
    Optional<Theatre> findBy_id(String _id);
    void deleteById(String _id);
    void deleteBy_id(String _id);
}