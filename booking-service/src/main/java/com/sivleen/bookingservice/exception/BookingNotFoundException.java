package com.sivleen.bookingservice.exception;

public class BookingNotFoundException extends Throwable {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
