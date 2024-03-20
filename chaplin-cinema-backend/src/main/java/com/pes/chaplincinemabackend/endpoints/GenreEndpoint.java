package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Genre;
import com.pes.chaplincinemabackend.entities.Movie;
import com.pes.chaplincinemabackend.repositories.GenreRepository;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage.ENTITY_DOES_NOT_EXIST;

@RestController
@RequestMapping(Paths.V1.Genres.fullPath)
public class GenreEndpoint {
    @Autowired
    private GenreRepository genreRepository;
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'THEATRE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Genre> getAllGenres() {
        return genreRepository.findGenres();
    }
}
