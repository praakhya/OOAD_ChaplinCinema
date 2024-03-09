package com.pes.chaplincinemabackend.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document("movie")
public class Movie {
    private String title;
    private String director;
    private List<String> crew;
    private List<String> cast;
    private float Rating;
    private String language;
    private String poster;
    private UUID id;
    Movie() {
        this.id = UUID.randomUUID();
    }

}
