package com.sivleen.paymentservice.controller;

import com.sivleen.paymentservice.model.Payment;
import com.sivleen.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestParam String paymentToken, @RequestBody Payment payment) {
        return paymentService.processPayment(payment, paymentToken);
    }

    /*@GetMapping("/status/{bookingId}")
    public ResponseEntity<String> getPaymentStatusByBookingId(@PathVariable Integer bookingId) {
        return paymentService.getPaymentStatusByBookingId(bookingId);
    }*/
}
