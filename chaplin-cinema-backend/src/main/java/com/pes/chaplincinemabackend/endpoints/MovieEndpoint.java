package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage.ENTITY_DOES_NOT_EXIST;

@RestController
@RequestMapping(Paths.V1.Movies.fullPath)
public class MovieEndpoint {
    @Autowired
    private MovieService movieService;
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> addMovieDetails(@RequestBody Movie movie) {
        return movieService.save(movie);
    }
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> updateMovieDetails(@RequestBody Movie movie) {
        return movieService.update(movie);
    }
    @RequestMapping(value = Paths.V1.Movies.GetOne, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> deleteMovieDetails(@PathVariable(Paths.V1.Movies.GetOnePathVariable) UUID movieId) {
        Movie movie = movieService.findOneByID(movieId).orElseThrow(() -> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(), movieId.toString()),
        String.format(ENTITY_DOES_NOT_EXIST.getReason(), movieId.toString())));
        movieService.deleteMovie(movie);
        return Optional.of(movie);
    }
}
