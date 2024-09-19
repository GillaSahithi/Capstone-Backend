package com.example.orderservice.service;

import com.example.orderservice.model.Payment;
import com.example.orderservice.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderService orderService;
    private final PaymentRepository paymentRepository;
    private RazorpayClient razorpayClient;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @PostConstruct
    public void init() throws Exception{
        this.razorpayClient=new RazorpayClient(razorpayKey, razorpaySecret);
    }

    //Create razorpay order
    public String createRazorpayOrder(double  amount, String currency, String customerId, String orderId, List<String> vendorIds) throws RazorpayException {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", (int)(amount * 100));
            options.put("currency", currency);
            options.put("payment_capture", 1);

            Order order = razorpayClient.orders.create(options);

            int orderAmount=order.get("amount");

            String paymentId = UUID.randomUUID().toString();

            Payment payment = new Payment();
            payment.setPaymentId(paymentId);
            payment.setOrderId(orderId);
            payment.setRazorpayOrderId(order.get("id"));
            payment.setAmount(orderAmount/100.0);
            payment.setCurrency(order.get("currency"));
            payment.setStatus("created");
            payment.setCustomerId(customerId);
            payment.setVendorIds(vendorIds);
            payment.setCreatedAt(LocalDateTime.now());

            paymentRepository.save(payment);

            return order.toString();
        }catch (RazorpayException e){
            throw new RazorpayException("Error creating razorpay order"+ e.getMessage());
        }
    }

    public void updatePaymentStatus(String razorpayOrderId, String status, String paymentId){
        Payment payment = paymentRepository.findByRazorpayOrderId(razorpayOrderId);
        if(payment!=null){
            payment.setStatus(status);
            payment.setPaymentId(paymentId);
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
        }
    }

    public List<Payment> getPaymentsByCustomerId(String customerId){
        return paymentRepository.findPaymentsByCustomerId(customerId);
    }

    public List<Payment> getPaymentsByVendorId(String vendorId){
        return paymentRepository.findPaymentsByVendorIds(vendorId);
    }
}
