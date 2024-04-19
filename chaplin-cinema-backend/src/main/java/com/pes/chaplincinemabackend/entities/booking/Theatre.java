package com.pes.chaplincinemabackend.entities.booking;

import com.pes.chaplincinemabackend.values.ScreeningTime;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Document(collection = "theatre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    @Id
    private String _id;
    private String id;
    private String theatreId;
    private String name;

    private List<String> shows;
    private String address;
    private String ImageUrl;
    private Map<ScreeningTime, String> showTimings = new HashMap<>();
    //@DBRef
    //private List<TheaterSeat> theaterSeatList = new ArrayList<>();


    public void allocateShowToTiming(ScreeningTime timing, String showId) {
        showTimings.put(timing, showId);
    }
    public String getShowForTiming(ScreeningTime timing) {
        return showTimings.get(timing);
    }
    public void addShow(String showId) {
        if (this.shows == null) {
            this.shows = new ArrayList<>();
        }
        this.shows.add(showId);
    }

    @Setter
    @Getter

    @DBRef
    private List<Shows> showsDetails;  // Add this field to store details of shows


}
