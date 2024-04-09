package com.pes.chaplincinemabackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document("genre")
public class Genre {
    @Id
    private Integer id;
    @Field("genre_name")
    private String genreName;
}
