package com.sivleen.paymentservice.service;

import com.sivleen.paymentservice.dao.PaymentDao;
import com.sivleen.paymentservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;

    @Autowired
    StripeService stripeService;
    public ResponseEntity<String> processPayment(Payment payment, String paymentToken) {
        String transactionId = generateTransactionId(payment.getBookingId());
        LocalDateTime paymentDate = LocalDateTime.now();
        String status = "PROCESSING";

        Payment paymentDetails = new Payment(transactionId, payment.getAmount(), payment.getCurrency(), payment.getBookingId(), paymentDate, status);

        //boolean paymentSuccessful = stripeService.processPayment(paymentToken, payment.getAmount(), payment.getCurrency());

        boolean paymentSuccessful = true;

        if (paymentSuccessful) {
            paymentDetails.setStatus("SUCCESS");
        } else {
            paymentDetails.setStatus("FAILURE");
        }

        paymentDao.save(paymentDetails);

        if(!paymentSuccessful){
            return new ResponseEntity<>("Payment Failed", HttpStatus.OK);
        }

        return new ResponseEntity<>("Payment successful", HttpStatus.OK);
    }

    /*public ResponseEntity<String> getPaymentStatusByBookingId(Integer bookingId) {
        Optional paymentOptional = paymentDao.findByBookingId(bookingId);
                //.orElseThrow(() -> new RuntimeException("Payment not found for booking ID: " + bookingId));
        if(paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            return new ResponseEntity<String>(payment.getStatus(), HttpStatus.OK);
        }
        return
    }*/

    private String generateTransactionId(Integer bookingId) {
        return "TXN_" + Integer.toString(bookingId) + LocalDateTime.now().toString();
    }
}

