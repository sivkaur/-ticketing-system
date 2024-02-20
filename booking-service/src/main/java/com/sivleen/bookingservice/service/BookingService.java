package com.sivleen.bookingservice.service;

import com.sivleen.bookingservice.dao.BookingDao;
import com.sivleen.bookingservice.exception.BookingException;
import com.sivleen.bookingservice.exception.BookingNotFoundException;
import com.sivleen.bookingservice.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingDao bookingDao;

    @Autowired
    EventClient eventClient;


    public ResponseEntity<String> createBooking(Booking booking) throws BookingException{
        if (booking.getNumSeats() <= 0) {
            throw new BookingException("Number of seats must be greater than 0");
        }

        bookingDao.save(booking);
        updateEventAvailability(booking.getEventId(),booking.getNumSeats());

        return new ResponseEntity<>("Booking created successfully", HttpStatus.OK);
    }

    public Booking getBooking(Integer bookingId) {
        return bookingDao.findById(bookingId).orElse(null);
    }

    public ResponseEntity<List<Booking>> getBookingsByUser(Integer userId) throws BookingNotFoundException{
        Optional<List<Booking>> bookings = bookingDao.findByUserId(userId);

        if(bookings.isPresent() && !bookings.get().isEmpty()) {
            List<Booking> userBookings = bookings.get();
            return new ResponseEntity<List<Booking>>(userBookings, HttpStatus.OK);
        }
        else{
            throw new BookingNotFoundException("Bookings not found");
        }
    }

    public ResponseEntity<String> cancelBooking(Integer bookingId){
        Booking booking = bookingDao.findById(bookingId).get();

        updateEventAvailability(booking.getEventId(), -(booking.getNumSeats()));
        bookingDao.deleteById(booking.getId());
        return new ResponseEntity<String>("Booking deleted successfully", HttpStatus.OK);
    }

    public boolean checkEventAvailability(Integer eventId, Integer numSeats) {
        Boolean availability = eventClient.checkEventAvailability(eventId,numSeats);
        return availability != null && availability;
    }

    public void updateEventAvailability(Integer eventId, Integer numSeats) {
        eventClient.updateEventAvailability(eventId, Map.of("seatsBooked", numSeats));
    }
}
