package org.example.productservice.services;

import org.example.productservice.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService{

    @Override
    public void doPayment(String email, String phone, Double amount, String orderId) {
        return;
    }
}
