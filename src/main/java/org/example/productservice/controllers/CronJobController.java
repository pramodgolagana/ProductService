package org.example.productservice.controllers;

import org.example.productservice.services.EmailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller

public class CronJobController {

    private EmailSender emailSender;

    CronJobController(EmailSender emailSender){
        this.emailSender=emailSender;
    }
    private String subject = "Friendly Reminder: Your Cart is Waiting for You!";
    private String recipient ="padalapraneeth39@gmail.com";
    private String body="Hi  Praneeth Padala,\n" +
            "\n" +
            "We noticed you have some items waiting in your cart! Just a quick reminder to check them out and make sure you don’t miss out on your favorite picks.\n" +
            "\n" +
            "Whether it's adding those finishing touches to your order or exploring more options, we're here to help make your shopping experience smooth and enjoyable. If you have any questions or need assistance, feel free to reach out.\n" +
            "\n" +
            "Thank you for shopping with us, and we hope to see you back soon!\n" +
            "\n" +
            "Best regards,\n" +
            "The [Pramod Ecomerse] Team";

    @Scheduled(initialDelay = 3000L,fixedRate = 2000L)
    void reminderFortheCartItems(){

        emailSender.sendEmail(recipient,subject,body);
    }
}
