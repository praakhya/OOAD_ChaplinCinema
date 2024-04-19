package com.pes.chaplincinemabackend.common.utils;

public class Constants {

    public static final String basePathOfPoster = "https://image.tmdb.org/t/p/w1280/%s";
    public static String pathOfPoster(String filename) {
        return String.format(basePathOfPoster, filename);
    }

}