package org.example.productservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 @Configuration
public class RazorpayConfiguration {
    @Value("${razorpay.key.id}")//This will fetch the information from the application.properties and will update the with the values in the properties file
    private  String razorpayaKeyId;
    @Value("${razorpay.key.secret}")
    private  String razorpayKeySecret;

    @Bean
    public RazorpayClient RazorpayConfiguration() throws RazorpayException {
        return new  RazorpayClient( razorpayaKeyId, razorpayKeySecret);
    }

}
