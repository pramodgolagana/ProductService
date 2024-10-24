package org.example.productservice.controllers;

import com.razorpay.RazorpayException;
import org.example.productservice.dto.InitiatePaymentRequestDto;
import org.example.productservice.dto.PaymentResponseDto;
import org.example.productservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
   //@Autowired
   private PaymentService paymentService;
   public void PaymentController( @Qualifier("razorpay")PaymentService paymentService){
       this.paymentService= paymentService;
   }

    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws Exception {

       paymentService.doPayment(requestDto.getEmail(), requestDto.getPhoneNumber(),
                                        requestDto.getAmount(), requestDto.getOrderId());
       return "Success";

    }

}
