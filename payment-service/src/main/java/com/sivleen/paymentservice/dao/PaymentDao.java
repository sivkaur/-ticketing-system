package com.sivleen.paymentservice.dao;

import com.sivleen.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    Optional<List<Payment>> findByBookingId(Integer bookingId);
}
