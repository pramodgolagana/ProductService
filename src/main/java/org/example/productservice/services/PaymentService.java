package org.example.productservice.services;

import com.razorpay.RazorpayException;
import org.example.productservice.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void doPayment(String email, String phone, Double amount, String orderId) throws Exception;
}
