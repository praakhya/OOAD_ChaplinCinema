package com.pes.chaplincinemabackend.theaterexceptions;

public class TheatreAlreadyExistsException extends RuntimeException{
    public TheatreAlreadyExistsException(String message){
        super(message);
    }
}
