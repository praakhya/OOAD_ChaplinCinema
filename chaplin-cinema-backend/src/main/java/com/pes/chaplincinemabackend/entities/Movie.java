package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.common.entities.AbstractBase;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document("movie")
public class Movie extends Event {
    @Field(name = "genre_ids")
    private List<Long> genreIds;
    @Field(name = "original_language")
    private String originalLanguage;
    @Field(name = "original_title")
    private String originalTitle;
    private Float popularity;
    @Field(name = "vote_average")
    private Float voteAverage;
    @Field(name = "vote_count")
    private Integer voteCount;
    private Integer movie_id;

}
