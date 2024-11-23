package org.example.productservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpayWebHook")
public class RazorpayWebHookController {

    @PostMapping("/")
    public void acceptWebHookRequest(){

        System.out.println("Razorpay WebHook triggered");
        //Make all the actions requred after payment once the method is called.
        return ;
    }
}
