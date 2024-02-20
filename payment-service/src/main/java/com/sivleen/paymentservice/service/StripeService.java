package com.sivleen.paymentservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public boolean processPayment(String paymentToken, double amount, String currency) {
        Stripe.apiKey = secretKey;

        //try {
            /*PaymentIntent intent = PaymentIntent.create(
                    PaymentIntentCreateParams.builder()
                            .setAmount((long) (amount * 100))
                            .setCurrency(currency)
                            .setPaymentMethod(paymentToken)
                            .setConfirm(true)
                            .build()
            );

            return "succeeded".equals(intent.getStatus());*/
            return true;
        /*} catch (StripeException e) {
            e.printStackTrace();
            return false;
        }*/
    }
}
