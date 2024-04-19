package com.pes.chaplincinemabackend.entities;


import com.pes.chaplincinemabackend.values.ScreeningTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


import java.util.List;
@Setter
@Getter
@Data
public class ShowRequest {
    // Getters and setters for the fields
    //private String showId;
    private String movieId;
    private String date;
    private ScreeningTime timing;
    private String category;
    private String theatreId;

}

