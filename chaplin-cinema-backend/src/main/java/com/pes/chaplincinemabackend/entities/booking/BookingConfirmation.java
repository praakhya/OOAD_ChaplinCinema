package com.pes.chaplincinemabackend.entities.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bookingConfirmation")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingConfirmation {
    private String bookingId;
    private String showId;
    private String movieName;
    private List<String> seats;
    private Integer totalPrice;
    private Double totalPayableAmount;
    private Boolean paymentStatus = false; // Default value set to false
    private Boolean cancellationStatus = false; // Default value set to false
    private String username;

    // Constructor with necessary fields
    public BookingConfirmation(String bookingId, String showId, String movieName, List<String> seats, Integer totalPrice, Double totalPayableAmount, String username) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.movieName = movieName;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.totalPayableAmount = totalPayableAmount;
        this.username = username;
    }

}