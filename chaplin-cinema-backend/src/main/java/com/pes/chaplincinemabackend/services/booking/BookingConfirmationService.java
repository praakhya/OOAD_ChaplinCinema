package com.pes.chaplincinemabackend.services.booking;


import com.pes.chaplincinemabackend.auth.repositiories.UserRepository;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.booking.BookingConfirmation;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import com.pes.chaplincinemabackend.repositories.booking.BookingConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingConfirmationService {

    @Autowired
    private BookingConfirmationRepository bookingConfirmationRepository;


    public void saveBookingConfirmation(Map<String, Object> bookingDetails, String username) {
        String bookingId = generateBookingId(); // Implement a method to generate a unique booking ID

        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId(bookingId);
        bookingConfirmation.setShowId((String) bookingDetails.get("showId"));
        bookingConfirmation.setMovieName((String) bookingDetails.get("movieName"));
        bookingConfirmation.setSeats((List<String>) bookingDetails.get("selectedSeat"));
        bookingConfirmation.setTotalPrice((Integer) bookingDetails.get("totalPrice"));
        bookingConfirmation.setTotalPayableAmount((Double) bookingDetails.get("totalPayableAmount"));
        bookingConfirmation.setPaymentStatus(true);
        bookingConfirmation.setCancellationStatus(false);
        bookingConfirmation.setUsername(username);

        bookingConfirmationRepository.save(bookingConfirmation);
    }

    private String generateBookingId() {
        // Implement logic to generate a unique booking ID
        return UUID.randomUUID().toString();
    }
    public List<BookingConfirmation> getBookingConfirmationByUsername(String username) {
        return bookingConfirmationRepository.getBookingConfirmationByUsername(username);
    }
}
