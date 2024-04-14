package com.pes.chaplincinemabackend.theaterexceptions;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String _id){
        super("Theatre not found with id: " + _id);
    }
}


