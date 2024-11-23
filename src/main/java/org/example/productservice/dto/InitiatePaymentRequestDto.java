package org.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
    private String email;
    private  String phoneNumber;
    private long amount;
    private long orderId;

}
