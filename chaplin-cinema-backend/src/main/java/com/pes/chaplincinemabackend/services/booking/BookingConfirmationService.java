package com.pes.chaplincinemabackend.services.booking;


import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.booking.BookingConfirmation;
import com.pes.chaplincinemabackend.entities.booking.Shows;
import com.pes.chaplincinemabackend.mvcendpoint.CustomerEndpoint;
import com.pes.chaplincinemabackend.repositories.booking.BookingConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pes.chaplincinemabackend.services.CustomerService;
import com.pes.chaplincinemabackend.services.booking.ShowsService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingConfirmationService {

    @Autowired
    private BookingConfirmationRepository bookingConfirmationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShowsService showsService;

    public List<BookingConfirmation> getBookingsByUsername(String username) {
        return bookingConfirmationRepository.findByUsername(username);
    }

    public void saveBookingConfirmation(Map<String, Object> bookingDetails, String username) {
        String bookingId = generateBookingId(); // Implement a method to generate a unique booking ID

        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId(bookingId);
        bookingConfirmation.setShowId((String) bookingDetails.get("showId"));
        bookingConfirmation.setTheatreName((String) bookingDetails.get("theaterName"));
        bookingConfirmation.setMovieName((String) bookingDetails.get("movieName"));
        bookingConfirmation.setSeats((List<String>) bookingDetails.get("selectedSeat"));
        bookingConfirmation.setTiming((String) bookingDetails.get("timing"));
        bookingConfirmation.setTotalPrice((Integer) bookingDetails.get("totalPrice"));
        bookingConfirmation.setTotalPayableAmount((Double) bookingDetails.get("totalPayableAmount"));
        bookingConfirmation.setPaymentStatus(true);
        bookingConfirmation.setCancellationStatus(false);
        bookingConfirmation.setUsername(username);

        bookingConfirmationRepository.save(bookingConfirmation);

        // Find the customer by username
        Optional<Customer> optionalCustomer = customerService.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Add the booking ID to the customer's bookings array
            customer.getBookings().add(UUID.fromString(bookingId));
            // Update the customer in the database
            customerService.save(customer);
        } else {
            // Handle case when customer is not found
            throw new UsernameNotFoundException("Customer not found with username: " + username);
        }
    }

    private String generateBookingId() {
        // Implement logic to generate a unique booking ID
        return UUID.randomUUID().toString();
    }

    public boolean cancelBooking(String bookingId, String username) {
        Optional<BookingConfirmation> optionalBookingConfirmation = bookingConfirmationRepository.findByBookingId(bookingId);
        if (optionalBookingConfirmation.isPresent()) {
            BookingConfirmation bookingConfirmation = optionalBookingConfirmation.get();
            if (!bookingConfirmation.getUsername().equals(username)) {
                // Booking does not belong to the current user
                return false;
            }
            // Set cancellation status to true
            bookingConfirmation.setCancellationStatus(true);
            // Free the selected seats
            Shows show = showsService.freeSelectedSeats(bookingConfirmation.getShowId(), bookingConfirmation.getSeats());
            // Remove the booking ID from the customer's bookings array
            Optional<Customer> optionalCustomer = customerService.findByUsername(username);
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                customer.getBookings().remove(UUID.fromString(bookingId));
                customerService.save(customer);
            } else {
                throw new UsernameNotFoundException("Customer not found with username: " + username);
            }
            // Delete the booking from the database
            bookingConfirmationRepository.deleteByBookingId(bookingId);
            return true; // Cancellation successful
        } else {
            // Booking not found with the given ID
            return false;
        }
    }

}
