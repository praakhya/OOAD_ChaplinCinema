package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.auth.entities.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;
@Document("user")
@Data
public class Customer extends User {
    Customer() {
        super();
        MoviesWatched = new ArrayList<>();
        Bookings = new ArrayList<>();
    }
    String firstName;
    String lastName;
    ArrayList<UUID> MoviesWatched;
    ArrayList<UUID> Bookings;

}
