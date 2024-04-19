package com.pes.chaplincinemabackend.services.booking;

import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.entities.booking.SeatMap;
import com.pes.chaplincinemabackend.entities.booking.Shows;
import com.pes.chaplincinemabackend.entities.booking.Theatre;
import com.pes.chaplincinemabackend.repositories.MovieRepository;
import com.pes.chaplincinemabackend.repositories.booking.ShowsRepository;
import com.pes.chaplincinemabackend.repositories.booking.TheatreRepository;
import com.pes.chaplincinemabackend.services.MovieService;
import com.pes.chaplincinemabackend.values.ScreeningTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class ShowsService {
    @Autowired
    private ShowsRepository showsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    private Map<String, Map<String, Object>> temporaryBookingData = new HashMap<>();
    public List<Shows> allShows() {

        return showsRepository.findAll();
    }
    public Shows getShowById(String showId) {
        return showsRepository.findShowBy_id(showId);
    }

    /*public Shows createShows(String showId, String movieId, String movieName, String date, String timing, String category) {
        return showsRepository.insert(new Shows(showId, movieId, movieName, date, timing, category));
    }*/
    public Shows createShows(String theatreId, String movieId, String movieName, String date, ScreeningTime timing, String category) {
        Theatre theatre = theatreRepository.findBy_id(theatreId).orElse(null);
        if (theatre == null) {
            throw new IllegalArgumentException("Theatre not found with ID: " + theatreId);
        }
        Shows existingShow = showsRepository.findByTheatreIdAndDateAndTimingsAndCategory(theatreId, date, timing, category);
        if (existingShow != null) {
            throw new IllegalArgumentException("A show already exists at this time and date in the specified theatre for this category.");
        }
        Movie movie = movieService.findByID(movieId);
        String poster=movie.getPoster();
        Shows newShow = new Shows(movieId, movieName, date, timing, category, theatreId,poster);
        Shows savedShow = showsRepository.insert(newShow);

        theatre.addShow(savedShow.get_id());
        theatreRepository.save(theatre);

        return savedShow;
    }

    public Shows updateShow(String showId, String movieId,String date, ScreeningTime timing, String category) {
        Shows existingShow = showsRepository.findShowBy_id(showId);
        if (existingShow == null) {
            throw new IllegalArgumentException("Show not found with ID: " + showId);
        }

        // Retrieve movie details from the movie database using movieId
        Movie movie = movieService.findByID(movieId);
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found with ID: " + movieId);
        }
        // Retrieve theatre ID from the existing show
        String theatreId = existingShow.getTheatreId();

        // Check if there is an existing show at the specified time and date in the specified theatre for the category
        Shows conflictingShow = showsRepository.findByTheatreIdAndDateAndTimingsAndCategory(theatreId, date, timing, category);
        if (conflictingShow != null && !conflictingShow.get_id().equals(showId)) {
            throw new IllegalArgumentException("A show already exists at this time and date in the specified theatre for this category.");
        }

        // Update show details
        existingShow.setMovieId(movie.getId());
        existingShow.setTitle(movie.getTitle());
        existingShow.setPoster(movie.getPoster());// Use movie title from the retrieved movie
        existingShow.setDate(date);
        existingShow.setTimings(timing);
        existingShow.setCategory(category);
        int price =1000;
        switch (category) {
            case "AUDI1":
                existingShow.setSeatMap(initSeatMap(5, 5, price));
                break;
            case "AUDI2":
                existingShow.setSeatMap(initSeatMap(4, 4, price));
                break;
            case "AUDI3":
                existingShow.setSeatMap(initSeatMap(3, 3, price));
                break;
            default:
                // Handle default case if necessary
                break;
        }

        return showsRepository.save(existingShow);
    }

    private ArrayList<ArrayList<SeatMap>> initSeatMap(int numRows, int numColumns, int price) {
        ArrayList<ArrayList<SeatMap>> seatMap = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            seatMap.add(new ArrayList<>()); // Add Row
            for (int j = 0; j < numColumns; j++) {
                SeatMap seat = new SeatMap(i + 1, j + 1, price, Boolean.FALSE);
                seatMap.get(i).add(seat); // Add column
            }
        }
        return seatMap;
    }

    public void deleteShow(String showId) {
        Shows showToDelete = showsRepository.findShowBy_id(showId);
        if (showToDelete == null) {
            throw new IllegalArgumentException("Show not found with ID: " + showId);
        }

        // Delete the show from the associated theatre
        Theatre associatedTheatre = theatreRepository.findByShowsContaining(showId);
        if (associatedTheatre != null) {
            associatedTheatre.getShows().remove(showId);
            theatreRepository.save(associatedTheatre);
        }

        // Delete the show itself
        showsRepository.delete(showToDelete);
    }


    public Shows updateShows(String _id, List<String> seats) {
        Shows show = showsRepository.findShowBy_id(_id);
        ArrayList<ArrayList<SeatMap>> sm = show.getSeatMap();
        int totalPrice = 0;
        List<String> selectedSeats = new ArrayList<>();
        for (String seat : seats) {
            String rowName = seat.substring(0, 1);
            String columnName = seat.substring(1, 2);

            for (ArrayList<SeatMap> row : sm) {
                for (SeatMap seatMap : row) {
                    if (rowName.equals(seatMap.getRowName()) && columnName.equals(seatMap.getColumnName())) {
                        seatMap.setBooked(true);
                        totalPrice += seatMap.getPrice();
                        String selectedSeat = rowName + columnName;
                        selectedSeats.add(selectedSeat);
                    }
                }
            }
        }
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("showId", _id);
        bookingDetails.put("selectedSeat", selectedSeats);
        bookingDetails.put("totalPrice", totalPrice);
        bookingDetails.put("numTickets",selectedSeats.size());

        // Save the changes to the database
        showsRepository.save(show);
        temporaryBookingData.put(_id, bookingDetails);
        System.out.println(bookingDetails);
        return show;
    }



    public Map<String, Object> getTemporaryBookingDetails(String _id) {
        Map<String, Object> bookingDetails = temporaryBookingData.get(_id);
        Shows show = showsRepository.findShowBy_id(_id);
        if (show != null) {
            // Extract movie name and theater name
            String movieName = show.getTitle();
            String theatreName = getTheatreNameForShow(_id);
            // Calculate total payable amount (18% of total price)
            int totalPrice = (int) bookingDetails.get("totalPrice");
            double totalPayableAmount = totalPrice * 1.18;


            // Add movie name, theater name, and total payable amount to bookingDetails map
            bookingDetails.put("movieName", movieName);
            bookingDetails.put("theaterName", theatreName);
            bookingDetails.put("totalPayableAmount", totalPayableAmount);
            System.out.println(bookingDetails);
        }

        return bookingDetails;
    }


    public Map<Theatre, List<Shows>> showByMovieIdAndDate(String movieId, String date) {
        List<Shows> shows = showsRepository.findShowByMovieIdAndDate(movieId, date);
        List<Theatre> theatres = mongoTemplate.findAll(Theatre.class);
        Map<Theatre, List<Shows>> theatreShowsMap = new HashMap<>();

        for (Theatre theatre : theatres) {
            List<String> theatreShows = theatre.getShows();
            List<Shows> matchingShows = new ArrayList<>();

            for (String showId : theatreShows) {
                for (Shows show : shows) {
                    if (show.get_id().equals(showId)) {
                        matchingShows.add(show);
                    }
                }
            }

            if (!matchingShows.isEmpty()) {
                theatreShowsMap.put(theatre, matchingShows);
            }
        }
        System.out.println(theatreShowsMap);
        return theatreShowsMap;
    }

    public ArrayList<ArrayList<SeatMap>> getSeatMapForShow(String id) {
        Shows show = showsRepository.findShowBy_id(id);
        if (show == null) {
            return null;
        }

        //Map<String, Object> seatMap = new HashMap<>();
        // ArrayList<ArrayList<SeatMap>> seatMap = new ArrayList<>();
        // seatMap.add(show.getSeatMap());
        return show.getSeatMap();
    }

    public String getTheatreNameForShow(String showId){
        List<Theatre> theatres = mongoTemplate.findAll(Theatre.class);
        String theatreName = "";
        for(Theatre theatre : theatres) {
            List<String> theatreShowsIds = theatre.getShows();
            if (theatreShowsIds.contains(showId)) {
                theatreName = theatre.getName();
            }
        }
        return theatreName;
    }


}

