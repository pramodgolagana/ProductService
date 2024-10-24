package org.example.productservice.services;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorpayPaymentService implements PaymentService{

    private final RazorpayClient razorpayClient;

    public  RazorpayPaymentService(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }

    @Override
    public void doPayment(String email, String phone, Double amount, String orderId) throws RazorpayException {

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",amount);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "receipt#1");

        JSONObject customer = new JSONObject();
        customer.put("email", email);
        customer.put("phone", phone);
        orderRequest.put("notes",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        orderRequest.put("notify", notify);

        orderRequest.put("callback_url", "https://domain.com/razorpayWebHook");
        orderRequest.put("callback_method", "post");

        Order order = razorpayClient.orders.create(orderRequest);


    }
}
