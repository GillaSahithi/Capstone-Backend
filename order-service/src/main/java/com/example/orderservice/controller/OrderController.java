package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.Payment;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        try{
            String customerId = createdOrder.getCustomerId();
            String orderId = createdOrder.getId();
            List<String> vendorIds = createdOrder.getVendorIds();
            double totalAmount = createdOrder.getTotalAmount();

            String paymentResponse = paymentService.createRazorpayOrder(
                    totalAmount,
                    "INR",
                    customerId,
                    orderId,
                    vendorIds
            );

            return ResponseEntity.ok(paymentResponse);
        }catch(RazorpayException e){
            return ResponseEntity.status(500).body("Error in initiating payment"+e.getMessage());
        }
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<?> initiatePayment(@PathVariable("orderId") String orderId) {
        Optional<Order> orderOpt = orderService.getOrderById(orderId);
        if(orderOpt.isPresent()){
            Order order = orderOpt.get();
            double totalAmount = order.getTotalAmount();
            String customerId = order.getCustomerId();
            List<String> vendorIds = order.getVendorIds();

            try{
                String paymentResponse = paymentService.createRazorpayOrder(
                        totalAmount,"INR",customerId,orderId,vendorIds
                );
                return ResponseEntity.ok(paymentResponse);
            }catch(RazorpayException e){
                return ResponseEntity.status(500).body("Error in initiating payment"+e.getMessage());
            }
        }
        return ResponseEntity.status(404).body("Order not found with id "+orderId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") String orderId) {
        Optional<Order> orderOpt = orderService.getOrderById(orderId);
        if(orderOpt.isPresent()){
            return ResponseEntity.ok(orderOpt.get());
        }
        return ResponseEntity.status(404).body("Order not found with id "+orderId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable("customerId") String customerId) {
        List<Order> orders = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String orderId, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.status(404).body("Order not found with id "+orderId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String orderId) {
        boolean deleted = orderService.deleteOrder(orderId);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        }
        return ResponseEntity.status(404).body("Order not found with id "+orderId);
    }

    @GetMapping("/order-items/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable("orderId") String orderId) {
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @PutMapping("/order-items/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable("id") String id, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderService.updateOrderItem(id, orderItem);
        if (updatedOrderItem != null) {
            return ResponseEntity.ok(updatedOrderItem);
        }
        return ResponseEntity.status(404).body("Order item not found with id "+id);
    }

    @DeleteMapping("/order-items/{itemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("itemId") String id) {
        boolean deleted = orderService.deleteOrderItem(id);
        if (deleted) {
            return ResponseEntity.ok("Order item deleted successfully");
        }
        return ResponseEntity.status(404).body("Order item not found with id "+id);
    }

    @GetMapping("/payments/customer/{customerId}")
    public ResponseEntity<?> getPaymentsByCustomerId(@PathVariable("customerId") String customerId) {
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customerId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments/vendor/{vendorId}")
    public ResponseEntity<?> getPaymentsByVendorId(@PathVariable("vendorId") String vendorId) {
        List<Payment> payments = paymentService.getPaymentsByVendorId(vendorId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
