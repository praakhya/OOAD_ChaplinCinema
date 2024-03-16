package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.common.entities.AbstractBase;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Event extends AbstractBase {
    private String overview;
    @Field(name = "poster_path")
    private String posterPath;
    @Field(name = "release_date")
    private Date releaseDate;
    private String title;
}
