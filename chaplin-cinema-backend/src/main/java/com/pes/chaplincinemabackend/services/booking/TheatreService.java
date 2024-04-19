package com.pes.chaplincinemabackend.services.booking;

import com.pes.chaplincinemabackend.entities.booking.Shows;
import com.pes.chaplincinemabackend.entities.booking.Theatre;
import com.pes.chaplincinemabackend.repositories.booking.TheatreRepository;
import com.pes.chaplincinemabackend.theaterexceptions.TheatreAlreadyExistsException;
import com.pes.chaplincinemabackend.theaterexceptions.TheatreNotFoundException;
import com.pes.chaplincinemabackend.values.ScreeningTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ShowsService showsService;

    public List<Theatre> allTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre updateTheatreByShowId(String theatreId, String showId, String operation) {
        Theatre th = theatreRepository.findTheatreByTheatreId(theatreId);

        if(th == null) {
            System.out.println("Cannot find Theatre: " + theatreId);
            return null;
        }

        if(operation.equals("create")) {
            th.getShows().add(showId);
        } else if(operation.equals("delete")) {
            th.getShows().remove(showId);
        }
        theatreRepository.save(th);
        return th;
    }

    public List<Theatre> getAllTheatres() {

        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(String _id) {
        Optional<Theatre> theatreOptional = theatreRepository.findBy_id(_id);
        return theatreOptional.orElseThrow(() -> new RuntimeException("Theatre not found with ID: " + _id));
    }

    public Theatre getTheatreByIdWithShowsDetails(String _id) {
        Optional<Theatre> optionalTheatre = theatreRepository.findBy_id(_id);
        if (optionalTheatre.isPresent()) {
            Theatre theatre = optionalTheatre.get();
            List<Shows> showsDetails = new ArrayList<>();
            List<String> showIds = theatre.getShows();
            if (showIds != null) {
                for (String showId : showIds) {
                    // Fetch show details from your ShowsRepository or service
                    Shows show = showsService.getShowById(showId); // Example method, implement as needed
                    if (show != null) {
                        showsDetails.add(show);
                    }
                }
            }
            theatre.setShowsDetails(showsDetails);
            return theatre;
        } else {
            // Handle theatre not found case as needed
            return null; // Or throw an exception or return a default Theatre instance
        }
    }

    public Theatre addTheatre(Theatre theatre) {
        if(TheatreAlreadyExists(theatre.getName())){
            throw new TheatreAlreadyExistsException(theatre.getName()+"Already exists");

        }

        return theatreRepository.save(theatre);
    }

    private boolean TheatreAlreadyExists(String name) {
        return theatreRepository.findByName(name).isPresent();
    }

    public Theatre updateTheatre(String _id, Theatre theatre) {
        return theatreRepository.findBy_id(_id).map(existingTheatre -> {

            existingTheatre.setName(theatre.getName());
            existingTheatre.setAddress(theatre.getAddress());
            existingTheatre.setImageUrl(theatre.getImageUrl());
            //existingTheatre.setShows(theatre.getShows());
            existingTheatre.setShowTimings(theatre.getShowTimings());
            return theatreRepository.save(existingTheatre);
        }).orElseThrow(() -> new TheatreNotFoundException("Theatre not found with id: " + _id));
    }
    public void deleteTheatre(String _id) {
        theatreRepository.deleteBy_id(_id);
    }
    public String getTheatreIdByTheatreName(String theatreName) {
        Optional<Theatre> theatreOptional = theatreRepository.findByName(theatreName);
        return theatreOptional.map(Theatre::get_id).orElse(null);
    }


    public void allocateShowToTiming(String theatreId, ScreeningTime timing, String showId) {
        Theatre theatre = theatreRepository.findBy_id(theatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre not found with id: " + theatreId));
        theatre.allocateShowToTiming(timing, showId);
        theatreRepository.save(theatre);
    }




}
