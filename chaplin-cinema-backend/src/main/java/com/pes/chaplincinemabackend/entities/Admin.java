package com.pes.chaplincinemabackend.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Admin extends User{
    void addMovieDetails(
            String Title,
            String Director,
            ArrayList<String> Crew,
            ArrayList<String> Cast,
            Float Rating,
            String Language,
            String Poster
    ) {};
    void updateMovieDetails(
            String Title,
            String Director,
            ArrayList<String> Crew,
            ArrayList<String> Cast,
            Float Rating,
            String Language,
            String Poster
    ) {};
    void deleteMovieDetails(UUID movieId) {}
}
