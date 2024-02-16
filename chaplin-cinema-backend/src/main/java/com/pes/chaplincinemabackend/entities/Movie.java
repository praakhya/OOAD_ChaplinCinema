package com.pes.chaplincinemabackend.entities;

import lombok.Builder;

import java.util.ArrayList;

@Builder()
public class Movie {
    private String title;
    private String director;
    private ArrayList<String> crew;
    private ArrayList<String> cast;
    private float Rating;
    private String language;
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCrew() {
        return crew;
    }

    public void addCrew(String crew) {
        this.crew.add(crew);
    }
    public void removeCrew(String crew) {
        this.crew.remove(crew);
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void addCast(String cast) {
        this.cast.add(cast);
    }
    public void removeCast(String cast) {
        this.cast.remove(cast);
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
