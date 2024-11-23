package org.example.productservice.services;

import com.razorpay.RazorpayException;
import org.example.productservice.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    String doPayment(String email, String phone, long amount, long orderId) throws Exception;
}
