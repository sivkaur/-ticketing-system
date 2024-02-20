package com.sivleen.bookingservice.dao;

import com.sivleen.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {
    Optional<List<Booking>> findByUserId(Integer userId);
}
