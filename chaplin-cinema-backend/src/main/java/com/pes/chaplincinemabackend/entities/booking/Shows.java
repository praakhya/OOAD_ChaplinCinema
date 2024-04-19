package com.pes.chaplincinemabackend.entities.booking;

import com.pes.chaplincinemabackend.values.ScreeningTime;
import lombok.*;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shows {
    @Getter
    @Setter
    @Id
    private String _id;

    private String movieId;

    private String title;

    private String date;

    private ScreeningTime timings;
    private String poster;

    private ArrayList<ArrayList<SeatMap>> seatMap;

    private String category;
    private String theatreId;

    private ArrayList<ArrayList<SeatMap>> initIMAXSeatMap() {
        int numRows = 5;
        int numColumns = 5;

        Integer price = 1000;
        ArrayList<ArrayList<SeatMap>> seatMap = new ArrayList<ArrayList<SeatMap>>();

        for(int i = 0; i < numRows; i++) {
            seatMap.add(new ArrayList<SeatMap> ()); //Add Row
            for(int j = 0; j < numColumns; j++) {
                SeatMap seat = new SeatMap(i+1, j+1, price, Boolean.FALSE);
                seatMap.get(i).add(seat); //add column
            }
        }
        return seatMap;
    }

    private ArrayList<ArrayList<SeatMap>> init4DSeatMap() {
        int numRows = 4;
        int numColumns = 4;

        Integer price = 1000;
        ArrayList<ArrayList<SeatMap>> seatMap = new ArrayList<ArrayList<SeatMap>>();

        for(int i = 0; i < numRows; i++) {
            seatMap.add(new ArrayList<SeatMap> ()); //Add Row
            for(int j = 0; j < numColumns; j++) {
                SeatMap seat = new SeatMap(i+1, j+1, price, Boolean.FALSE);
                seatMap.get(i).add(seat); //add column
            }
        }
        return seatMap;
    }

    private ArrayList<ArrayList<SeatMap>> initPVRSeatMap() {
        int numRows = 3;
        int numColumns = 3;

        Integer price = 1000;
        ArrayList<ArrayList<SeatMap>> seatMap = new ArrayList<ArrayList<SeatMap>>();

        for(int i = 0; i < numRows; i++) {
            seatMap.add(new ArrayList<SeatMap> ()); //Add Row
            for(int j = 0; j < numColumns; j++) {
                SeatMap seat = new SeatMap(i+1, j+1, price, Boolean.FALSE);
                seatMap.get(i).add(seat); //add column
            }
        }
        return seatMap;

    }

    public Shows(String movieId, String movieTitle, String date, ScreeningTime timing, String category, String theatreId,String poster) {
        this.movieId = movieId;
        this.title = movieTitle;
        this.date = date;
        this.timings = timing;
        this.category = category;
        this.theatreId = theatreId;
        this.poster=poster;
        switch (category) {
            case "AUDI1":
                this.seatMap = initIMAXSeatMap();
                break;
            case "AUDI2":
                this.seatMap = init4DSeatMap();
                break;
            case "AUDI3":
                this.seatMap = initPVRSeatMap();
                break;
            default:
                break;
        }
    }
}