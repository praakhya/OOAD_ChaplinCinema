package com.pes.chaplincinemabackend.mvcendpoint.booking;

import com.pes.chaplincinemabackend.entities.booking.Theatre;
import com.pes.chaplincinemabackend.services.MovieService;
import com.pes.chaplincinemabackend.services.booking.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieService movieService;




    @RequestMapping()
    public String getAllTheatres(Model model) {
        List<Theatre> theatres = theatreService.getAllTheatres();
        model.addAttribute("theatres", theatres);
        theatres.forEach(System.out::println);
        return "theatres";
    }
    @RequestMapping("/withShows/{id}") //get theatre by id
    public String getTheatreWithShows(@PathVariable("id") String theatreId, Model model) {
        Theatre theatre = theatreService.getTheatreByIdWithShowsDetails(theatreId);
        model.addAttribute("theatre", theatre);
        System.out.println(theatre);
        return "theatredetails";
    }
    @RequestMapping("/add")
    public String addTheatre(Model model){
        try {
            Theatre theatre = new Theatre();
            model.addAttribute("theatre", theatre);
            return "new_theatre";
        }
        catch (IllegalArgumentException e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "movie_exists";
        }
    }
    @RequestMapping(value="theatres/add", method= RequestMethod.POST)
    public String addTheatre_1(@ModelAttribute Theatre theatre, Model model) {
        System.out.println(theatre);
        theatreService.addTheatre(theatre);
        return "redirect:/theatres";
    }

    @PostMapping("/{theatreId}/{showId}/{operation}")
    public ResponseEntity <Theatre> updateTheatreByShowId(@PathVariable String theatreId, @PathVariable String showId,
                                                          @PathVariable String operation) {
        return new ResponseEntity<Theatre>(theatreService.updateTheatreByShowId(theatreId, showId, operation), HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public String updateTheatre(@PathVariable(value="id") String id, Model model) {
        Theatre theatre = theatreService.getTheatreById(id);
        model.addAttribute("theatre", theatre);

        return "updateTheatre";
    }
    @PostMapping("/edit/{id}")
    public String updateTheatre(@PathVariable String id, @ModelAttribute("theatre") Theatre theatre) {
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatre);
        System.out.println(updatedTheatre);
        return "redirect:/theatres";}
    @GetMapping("/delete/{id}")
    public String deleteTheatre(Model model,@PathVariable (value="id")String _id) {
        theatreService.deleteTheatre(_id);
        return "redirect:/theatres";
    }



}