package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.values.ScreeningTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ShowUpdateRequest {
    private String showId;
    private String movieId;
    private String movieName;
    private String date;
    private ScreeningTime timing;
    private String category;

}
