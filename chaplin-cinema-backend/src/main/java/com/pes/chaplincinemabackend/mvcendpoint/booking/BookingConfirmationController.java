package com.pes.chaplincinemabackend.mvcendpoint.booking;

import com.pes.chaplincinemabackend.entities.booking.BookingConfirmation;
import com.pes.chaplincinemabackend.services.booking.BookingConfirmationService;
import com.pes.chaplincinemabackend.services.booking.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class BookingConfirmationController {

    @Autowired
    private BookingConfirmationService bookingConfirmationService;

    @Autowired
    private ShowsService showsService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/payment/success")
    public String paymentSuccess(@RequestParam String showId, Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("error", "User not authenticated");
            return "error"; // Assuming you have an error.html Thymeleaf template to display error messages
        }

        String username = principal.getName(); // Get the username from Principal

        // Logic to handle payment success
        Map<String, Object> bookingDetails = showsService.getTemporaryBookingDetails(showId);
        if (bookingDetails != null && !bookingDetails.isEmpty()) {
            bookingConfirmationService.saveBookingConfirmation(bookingDetails, username); // Pass the username to the service method
            model.addAttribute("message", "Booking confirmation saved successfully");
            model.addAttribute("bookingDetails", bookingDetails);
            return "paymentSuccess";
        } else {
            model.addAttribute("error", "Failed to retrieve booking details");
            return "error"; // Assuming you have an error.html Thymeleaf template to display error messages
        }
    }

    @GetMapping("/bookings")
    public String getAllBookingsByUsername(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("error", "User not authenticated");
            return "error"; // Assuming you have an error.html Thymeleaf template to display error messages
        }

        String username = principal.getName(); // Get the username from Principal

        // Retrieve all bookings for the user
        List<BookingConfirmation> bookings = bookingConfirmationService.getBookingsByUsername(username);

        model.addAttribute("bookings", bookings);
        return "bookings"; // Assuming you have a bookings.html Thymeleaf template to display bookings
    }


    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/bookings/delete")
    public ResponseEntity<String> deleteBooking(@RequestParam String bookingId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        String username = principal.getName();

        // Handle cancellation and deletion in the service layer
        boolean cancellationSuccess = bookingConfirmationService.cancelBooking(bookingId, username);

        if (cancellationSuccess) {
            return ResponseEntity.ok("Booking cancelled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel booking");
        }
    }


}