package com.pes.chaplincinemabackend.entities.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bookingRequest")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String theatreName;
    private String showId;
    private List<String> seats;
    private Boolean status;
}