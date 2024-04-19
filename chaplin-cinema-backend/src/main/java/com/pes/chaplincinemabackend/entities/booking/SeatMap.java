package com.pes.chaplincinemabackend.entities.booking;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seatMap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatMap {
    private Integer rowId;
    private String rowName; //A, B, C etc
    private Integer columnId;
    private String columnName; //1,2,3 etc
    private Integer price;
    public Boolean booked;

    public SeatMap(Integer rowId, Integer columnId, Integer price, Boolean free) {
        this.rowId = rowId;
        this.rowName = Character.toString((char) ('A' + rowId - 1));
        this.columnId = columnId;
        this.columnName = Integer.toString(columnId);
        this.price = price;
        this.booked = free;
    }
}