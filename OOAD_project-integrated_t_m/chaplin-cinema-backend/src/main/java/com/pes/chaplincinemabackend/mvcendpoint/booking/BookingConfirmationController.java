package com.pes.chaplincinemabackend.mvcendpoint.booking;

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
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class BookingConfirmationController {

    @Autowired
    private BookingConfirmationService bookingConfirmationService;

    @Autowired
    private ShowsService showsService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/success")
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
            return "paymentSuccess"; // Assuming you have a paymentSuccess.html Thymeleaf template
        } else {
            model.addAttribute("error", "Failed to retrieve booking details");
            return "error"; // Assuming you have an error.html Thymeleaf template to display error messages
        }
    }
}