package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.common.entities.AbstractBase;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Event {
    @Id
    private ObjectId id;
    private String overview;
    private String poster;
    private Date released;
    private String title;
}
