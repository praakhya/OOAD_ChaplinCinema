package com.pes.chaplincinemabackend.mvcendpoint.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.entities.ShowRequest;
import com.pes.chaplincinemabackend.entities.ShowUpdateRequest;
import com.pes.chaplincinemabackend.entities.booking.SeatMap;
import com.pes.chaplincinemabackend.entities.booking.Shows;
import com.pes.chaplincinemabackend.entities.booking.Theatre;
import com.pes.chaplincinemabackend.services.MovieService;
import com.pes.chaplincinemabackend.services.booking.ShowsService;
import com.pes.chaplincinemabackend.services.booking.TheatreService;
import com.pes.chaplincinemabackend.values.ScreeningTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private ShowsService showsService;

    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;

    @GetMapping
    public ResponseEntity<List<Shows>> getAllShows() {
        return new ResponseEntity<List<Shows>>(showsService.allShows(), HttpStatus.OK);
    }



    //Return Map of Theatre (key) and List of shows (value)
   /* @GetMapping("display/{movieId}/{date}")
    public ResponseEntity<Map<Theatre, List<Shows>>> getTheatreAndTimings(@PathVariable Integer movieId, @PathVariable String date) {
        return new ResponseEntity<Map<Theatre, List<Shows>>>(showsService.showByMovieIdAndDate(movieId, date),HttpStatus.OK);
    }*/

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/display/{movieId}")
    public String display(@PathVariable String movieId, Model model) {
        model.addAttribute("TimingsDisplay.html");
        return "TimingsDisplay.html";
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/display/{movieId}/{date}")
    public String displayTheatreTimings(@PathVariable String movieId, @PathVariable String date, Model model) {
        model.addAttribute("theatreTimings", showsService.showByMovieIdAndDate(movieId, date));
        return "theatre-timings";
    }

    @PostMapping("/{showId}/{movieId}/{movieName}/{date}/{timing}/{category}")
    public ResponseEntity<Shows> createShows(@PathVariable String showId, @PathVariable String movieId, @PathVariable String movieName,
                                             @PathVariable String date, @PathVariable ScreeningTime timing, @PathVariable String category) {
        return new ResponseEntity<Shows>(showsService.createShows(showId, movieId, movieName, date, timing, category), HttpStatus.OK);
    }


    @GetMapping("/{showId}/TheatreName")
    public ResponseEntity<String> getTheatreNameForShow (@PathVariable String showId)
    {
        return new ResponseEntity<String>(showsService.getTheatreNameForShow(showId),HttpStatus.OK);
    }

    // add exception
 /*   @PostMapping("/{bookMyShow}")
    public ResponseEntity <Shows> updateShows(@RequestBody BookingRequest payLoad) throws JsonProcessingException {
        return new ResponseEntity<Shows>(showsService.updateShows(payLoad.getShowId(), payLoad.getSeats()), HttpStatus.OK);
    }
*/
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/bookMyShow")
    public String bookMyShow(@RequestParam("showId") String showId, @RequestParam("selectedSeats") String selectedSeats) {
        List<String> selectedSeatsList = Arrays.asList(selectedSeats.split(","));
        Shows show = showsService.updateShows(showId, selectedSeatsList);
        return "bookMyShow";
    }
    /*@GetMapping("/{showId}/bookingSubmission")
    public ResponseEntity<Map<String, Object>> getBookingSubmissionDetails(@PathVariable String showId) {
        Map<String, Object> bookingDetails = showsService.getTemporaryBookingDetails(showId);
        return ResponseEntity.ok(bookingDetails);
    }*/

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/bookingSubmission")
    public String getBookingSubmissionDetails(@RequestParam("showId") String showId, Model model) {
        // Retrieve booking details
        Map<String, Object> bookingDetails = showsService.getTemporaryBookingDetails(showId);
        model.addAttribute("bookingDetails", bookingDetails);
        return "paySummary";
    }


    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/seatDetails")
    public String displayShowDetails(@RequestParam String showId, Model model) {
        ArrayList<ArrayList<SeatMap>> seatMap = showsService.getSeatMapForShow(showId);
        if (seatMap == null) {
            return "error";
        }
        System.out.println("Seat Map: " + seatMap.toString());
        model.addAttribute("seatMap", seatMap);
        return "seatDetails";
    }
//theatre show part
    @GetMapping("view/{showId}")
    public String getShowById(@PathVariable String showId, Model model) {
        Shows show = showsService.getShowById(showId);
        System.out.println(show);
        if (show != null) {
            model.addAttribute("show", show);

            return "showDetails";
        } else {

            return "redirect:/theatres";
        }
    }

    @GetMapping("/{movieId}/{date}")
    public ResponseEntity<Map<Theatre, List<Shows>>> getTheatreAndTimings(@PathVariable String movieId, @PathVariable String date) {
        return new ResponseEntity<Map<Theatre, List<Shows>>>(showsService.showByMovieIdAndDate(movieId, date),HttpStatus.OK);
    }

    @GetMapping("/addshows/{theatreId}")
    public String showAddShowForm(Model model, @PathVariable("theatreId") String theatreId) {
        // Get all movies from the database
        Page<Movie> movies = movieService.findAll(0,50);
        Theatre theatre=theatreService.getTheatreById(theatreId);
        // Check if no movies found
        if (movies.isEmpty()) {
            // Handle the case where no movies are found
            model.addAttribute("errorMessage", "No movies found");
            return "error"; // Assuming you have an error.html or error.html Thymeleaf template
        }

        // Pass movies list and a new ShowRequest object to the view
        model.addAttribute("movies", movies);
        model.addAttribute("showRequest", new ShowRequest());
        model.addAttribute("theatre", theatre);

        // Pass ScreeningTime enum values to the view
        List<ScreeningTime> screeningTimes = Arrays.asList(ScreeningTime.values());
        model.addAttribute("screeningTimes", screeningTimes);

        return "addShow";
    }
    @PostMapping("/addshows/{theatreId}")
    public String processAddShowForm(@ModelAttribute("showRequest") ShowRequest request,
                                     Model model, @PathVariable("theatreId") String theatreId) {
        System.out.println(model);
        String movieId = request.getMovieId();
        Movie movie = movieService.findByID(movieId);

        if (movie == null) {
            model.addAttribute("errorMessage", "Movie not found");
            return "error"; // Handle movie not found
        }

        String movieName = movie.getTitle();
        String date = request.getDate();
        ScreeningTime timing = request.getTiming();
        String category = request.getCategory();
        System.out.println(theatreId+ movieId+movieName+ date+timing+category);
        try {
            Shows newShow = showsService.createShows(theatreId, movieId, movieName, date, timing, category);
            model.addAttribute("show", newShow);
            return "redirect:/theatres/withShows/" + theatreId; // Show details page
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_add"; // Handle errors
        }


    }

    @GetMapping("/edit/{showId}")
    public String showEditForm(@PathVariable String showId, Model model) {
        Shows show = showsService.getShowById(showId);
        if (show == null) {
            model.addAttribute("errorMessage", "Show not found");
            return "error"; // Handle the error case
        }
        ShowUpdateRequest updateRequest=new ShowUpdateRequest();
        updateRequest.setShowId(showId);
        updateRequest.setDate(show.getDate());
        updateRequest.setCategory(show.getCategory());
        updateRequest.setTiming(show.getTimings());
        updateRequest.setMovieId(show.getMovieId());
        Movie movie=movieService.findByID(show.getMovieId());
        updateRequest.setMovieName(movie.getTitle());

        // Populate the model with show details
        model.addAttribute("show", show);
        model.addAttribute("movies", movieService.findAll(0,50)); // Add movies for dropdown
        model.addAttribute("updateRequest",updateRequest);
        model.addAttribute("screeningTimes", Arrays.asList(ScreeningTime.values())); // Add screening times
        return "editShow";
    }

    @PostMapping("/edit/{showId}")
    public String processEditForm(@PathVariable String showId,
                                  @ModelAttribute("updateRequest") ShowUpdateRequest request,
                                  Model model) {
        try {
            String movieName = request.getMovieName();
            Movie movie = movieService.getMovieByName(movieName);
            if (movie == null) {
                throw new IllegalArgumentException("Movie not found");
            }

            String movieId = movie.getId();
            String date = request.getDate();
            ScreeningTime timing = request.getTiming();
            String category = request.getCategory();

            Shows updatedShow = showsService.updateShow(showId, movieId, date, timing, category);
            model.addAttribute("show", updatedShow);
            String theatreId = updatedShow.getTheatreId();
            return "redirect:/theatres/withShows/" + theatreId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_edit"; // Handle errors
        }
    }

    @Getter
    @Setter
    public static class UpdateTheatreRequest {
        private String newTheatreId;

    }



    @GetMapping("/delete/{id}")
    public String deleteShow(Model model,@PathVariable (value="id")String _id) {
        Shows show=showsService.getShowById(_id);
        String theatreId=show.getTheatreId();
        showsService.deleteShow(_id);
        return "redirect:/theatres/withShows/" + theatreId;
    }






}