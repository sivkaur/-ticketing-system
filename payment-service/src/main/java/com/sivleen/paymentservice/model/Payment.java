package com.sivleen.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private double amount;
    private String currency;
    private LocalDateTime paymentDate;
    private String status;
    private Integer bookingId;

    public Payment(String transactionId, double amount, String currency, Integer bookingId, LocalDateTime paymentDate, String status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.status = status;
        this.bookingId = bookingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Payment() {
    }

    public Payment(Long id, String transactionId, double amount, String currency, LocalDateTime paymentDate, String status, Integer bookingId) {
        this.id = id;
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.status = status;
        this.bookingId = bookingId;
    }
}
