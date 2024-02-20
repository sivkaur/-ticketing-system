package com.sivleen.bookingservice.controller;

import com.sivleen.bookingservice.exception.BookingException;
import com.sivleen.bookingservice.exception.BookingNotFoundException;
import com.sivleen.bookingservice.model.Booking;
import com.sivleen.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        try {
            boolean isAvailable = bookingService.checkEventAvailability(booking.getEventId(), booking.getNumSeats());

            if (isAvailable) {
                return bookingService.createBooking(booking);
            } else {
                return new ResponseEntity<>("Event not available for booking", HttpStatus.BAD_REQUEST);
            }

        } catch (BookingException e) {
            return new ResponseEntity<String>("Booking creation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Integer bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        if (booking != null) {
            return new ResponseEntity<Booking>(booking,HttpStatus.OK);
        } else {
            return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Integer userId) {
        try{
            return bookingService.getBookingsByUser(userId);
        }
        catch (BookingNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        if (booking != null) {
            return bookingService.cancelBooking(bookingId );
        } else {
            return new ResponseEntity<>("Booking doesn't exist",HttpStatus.BAD_REQUEST);
        }
    }
}

