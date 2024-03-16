package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.auth.entities.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;
@Document("user")
@Data
public class Customer extends User {
    public Customer() {
        MoviesWatched = new ArrayList<>();
        Bookings = new ArrayList<>();
    }

    private String firstName;
    private String lastName;
    private ArrayList<UUID> MoviesWatched;
    private ArrayList<UUID> Bookings;

}
