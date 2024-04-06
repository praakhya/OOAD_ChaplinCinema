package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/search")
    public String getMovieBySubstring(Model model, @RequestParam("substr") String substr, @RequestParam(value = "page",defaultValue = "0", required = true) int page, @RequestParam(value = "size",defaultValue = "50", required = true) int size) {
        model.addAttribute("movies",movieService.searchByMoviePhrase(substr,page,size));
        return "movies";
    }

}
