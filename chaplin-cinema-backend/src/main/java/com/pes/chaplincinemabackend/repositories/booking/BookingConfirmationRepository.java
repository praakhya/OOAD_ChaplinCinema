package com.pes.chaplincinemabackend.repositories.booking;

import com.pes.chaplincinemabackend.entities.booking.BookingConfirmation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingConfirmationRepository extends MongoRepository<BookingConfirmation, ObjectId> {

    @Query("{username: ?0}")
    List<BookingConfirmation> getBookingConfirmationByUsername(String username);
    List<BookingConfirmation> findByUsername(String username);

    Optional<BookingConfirmation> findByBookingId(String bookingId);

    void deleteByBookingId(String bookingId);
}