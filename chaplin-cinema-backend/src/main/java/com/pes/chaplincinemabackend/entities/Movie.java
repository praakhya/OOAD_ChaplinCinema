package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.common.entities.AbstractBase;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document("movie")
public class Movie extends Event {
    @Field(name ="fullplot")
    private String overview;
    private List<String> genres;
    private Integer runtime;
    private List<String> cast;
    @Field(name = "languages")
    private List<String> languages;
    private List<String> directors;
    private List<String> writers;
    private Integer year;
    private IMDB imdb;
    private List<String> countries;
    private String type;
    private Tomatoes tomatoes;

}
