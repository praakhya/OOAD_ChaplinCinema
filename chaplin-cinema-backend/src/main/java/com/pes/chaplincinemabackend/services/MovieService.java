package com.pes.chaplincinemabackend.services;

import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }
    public Movie findByID(String id) {
        return movieRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getReason(), id),
                String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(), id)));
    }

    public Page<Movie> findAll(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size));
    }
    public Page<Movie> searchByMoviePhrase(String phrase, int page, int size) {
        return movieRepository.searchByMoviePhrase(phrase, PageRequest.of(page, size));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Optional<Movie> save(Movie movie) {
        if (movieRepository.findById(movie.getId()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(ExceptionMessage.ENTITY_ALREADY_EXISTS.getReason(), movie.getId()),
                    String.format(ExceptionMessage.ENTITY_ALREADY_EXISTS.getError(), movie.getId()));
        }
        return Optional.of(movieRepository.save(movie));
    }
    public Optional<Movie> update(final Movie movie) {
        Movie storedMovie = movieRepository
                .findById(movie.getId())
                .orElseThrow(()-> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(),movie.getId()),
                        String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getReason(),movie.getId())));
        storedMovie = movieRepository.save(movie);
        return Optional.of(storedMovie);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteMovie(Movie movie) {
        movieRepository.deleteById(movie.getId());
    }
}
