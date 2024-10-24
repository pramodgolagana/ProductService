package org.example.productservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpayWebHook")
public class RazorpayWebHookController {

    @PostMapping("/")
    public ResponseEntity acceptWebHookRequest(){
        // redirect to UI
        System.out.println("Call back URL is called.");
        return null;
    }
}
