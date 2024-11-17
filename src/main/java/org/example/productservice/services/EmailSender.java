package org.example.productservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailSender {
    private JavaMailSender javaMailSender;

    EmailSender(JavaMailSender javaMailSender){
        this.javaMailSender= javaMailSender;
    }
    @Value("${spring.mail.username}")
    private String fromEmailId;
    public void sendEmail(String recipient,String subject, String body){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailId);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);


    }

}
