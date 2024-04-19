package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Genre;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    @RequestMapping("/{id}")
    public String getMovieByID(Model model, @PathVariable("id") String id) {
        model.addAttribute("movie",movieService.findByID(id));
        return "movie";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String deleteMovieByID(Model model, @PathVariable("id") String id) {
        Movie movie = movieService.findByID(id);
        movieService.deleteMovie(movie);
        return "movies";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping("/edit/{id}")
    public String getMovieByIDForEdit(Model model, @PathVariable("id") String id) {
        model.addAttribute("movie",movieService.findByID(id));
        return "movieEdit";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping("/add")
    public String addMoviePage(Model model) {
        model.addAttribute("movie",new Movie());
        return "movieAdd";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
        storedMovie.setPoster(movie.getPoster());
        storedMovie = movieService.update(storedMovie).get();
        model.addAttribute("movie", movieService.update(storedMovie).get());
        return "movie";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String addMovie(
            @ModelAttribute Movie movie,
            Model model) {
        System.out.println("POSTING NEW MOVIE"+movie);
        movieService.save(movie);
        return "movie";
    }
    @RequestMapping("/search")
    public String getMovieBySubstring(Model model, @RequestParam("substr") String substr, @RequestParam(value = "page",defaultValue = "0", required = true) int page, @RequestParam(value = "size",defaultValue = "50", required = true) int size) {
        model.addAttribute("movies",movieService.searchByMoviePhrase(substr,page,size));
        return "movies";
    }

}
