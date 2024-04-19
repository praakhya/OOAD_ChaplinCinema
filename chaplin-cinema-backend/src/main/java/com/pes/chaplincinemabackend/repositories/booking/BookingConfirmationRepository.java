package com.pes.chaplincinemabackend.repositories.booking;

import com.pes.chaplincinemabackend.entities.booking.BookingConfirmation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingConfirmationRepository extends MongoRepository<BookingConfirmation, ObjectId> {

    @Query("{username: ?0}")
    List<BookingConfirmation> getBookingConfirmationByUsername(String username);
}