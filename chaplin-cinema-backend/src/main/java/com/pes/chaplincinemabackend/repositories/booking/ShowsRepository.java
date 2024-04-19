package com.pes.chaplincinemabackend.repositories.booking;


import com.pes.chaplincinemabackend.entities.booking.Shows;
import com.pes.chaplincinemabackend.values.ScreeningTime;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowsRepository extends MongoRepository<Shows, ObjectId> {

    List<Shows> findShowByMovieIdAndDate(String movieId, String date);
    Shows findShowBy_id(String _id);
    Shows findShowByMovieId(String movieId);
    boolean existsByCategoryAndDateAndTimings(String category, String date, ScreeningTime timings);

    Shows findByTheatreIdAndDateAndTimingsAndCategory(String theatreId, String date, ScreeningTime timings, String category);



}
