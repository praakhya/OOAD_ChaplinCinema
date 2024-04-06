package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping(Paths.Movies.Base)
public class MoviesEndpoint {
    @Autowired
    private MovieService movieService;
    @RequestMapping()
    public String getAllMovies(Model model) {
        model.addAttribute("movies",movieService.findAll(0,50));
        return "movies";
    }
    @RequestMapping("/{id}")
    public String getMovieByID(Model model, @PathVariable("id") String id) {
        model.addAttribute("movie",movieService.findByID(id));
        return "movie";
    }
    @RequestMapping("/edit/{id}")
    public String getMovieByIDForEdit(Model model, @PathVariable("id") String id) {
        model.addAttribute("movie",movieService.findByID(id));
        return "movieEdit";
    }
    @RequestMapping("/edit")
    public String editMovie(@ModelAttribute Movie movie,
                            Model model) {
        Movie storedMovie = movieService.findByID(movie.getId());
        storedMovie.setTitle(movie.getTitle());
        storedMovie.setOverview(movie.getOverview());
        storedMovie.setGenres(movie.getGenres());
        storedMovie.setCast(movie.getCast());
        storedMovie.setDirectors(movie.getDirectors());
        storedMovie.setWriters(movie.getWriters());
        storedMovie.setImdb(movie.getImdb());
        storedMovie = movieService.update(storedMovie).get();
        model.addAttribute("movie", movieService.update(storedMovie).get());
        return "movie";
    }
    @RequestMapping("/search")
    public String getMovieBySubstring(Model model, @RequestParam("substr") String substr, @RequestParam(value = "page",defaultValue = "0", required = true) int page, @RequestParam(value = "size",defaultValue = "50", required = true) int size) {
        model.addAttribute("movies",movieService.searchByMoviePhrase(substr,page,size));
        return "movies";
    }

}
