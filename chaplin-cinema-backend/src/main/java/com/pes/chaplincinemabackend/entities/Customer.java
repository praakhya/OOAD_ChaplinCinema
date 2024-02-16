package com.pes.chaplincinemabackend.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Customer extends User{
    String username;
    String password;
    String firstName;
    String lastName;
    ArrayList<UUID> MoviesWatched;
    ArrayList<UUID> Bookings;
    void makeBooking (UUID movieId, UUID theaterId, Seat seat) {

    }
    void cancelBooking (UUID bookingId) {

    }
    Ticket getTicket() {
        return new Ticket();
    }
}
