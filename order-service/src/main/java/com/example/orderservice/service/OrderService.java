package com.example.orderservice.service;

import com.example.orderservice.dto.Customer;
import com.example.orderservice.dto.Product;
import com.example.orderservice.dto.Vendor;
import com.example.orderservice.exceptions.OrderNotFoundException;
import com.example.orderservice.feign.CustomerClient;
import com.example.orderservice.feign.ProductClient;
import com.example.orderservice.feign.VendorClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmailSenderService emailSenderService;
    private final CustomerClient customerClient;
    private final VendorClient vendorClient;
    private final ProductClient productClient;

    public Order createOrder(Order order) {
        if(order.getOrderDate()==null){
            order.setOrderDate(LocalDateTime.now());
        }
        order=orderRepository.save(order);
        System.out.println("Order Created with id: "+order.getId());

        double totalAmount=0.0;
        Set<String> vendorIds=new HashSet<>();

        for (OrderItem item:order.getOrderItems()) {
            Map<String, Object> product = productClient.getProductById(item.getProductId());
            System.out.println("Fetched Product Details for Product Id: "+item.getProductId());

            int stockQuantity = (int) product.get("stockQuantity");
            double price = (double) product.get("price");
            String productName = (String) product.get("name");
            String vendorId = (String) product.get("vendorId");

            if (stockQuantity<item.getQuantity()){
                throw new IllegalStateException("Product "+productName+" is out of stock");
            }

            item.setPrice(price);
            item.setProductName(productName);
            item.setOrderId(order.getId());
            item.setVendorId(vendorId);

            orderItemRepository.save(item);

            productClient.updateProductStock(item.getProductId(),stockQuantity-item.getQuantity());

            totalAmount+=price*item.getQuantity();

            vendorIds.add(vendorId);
        }
        order.setTotalAmount(totalAmount);
        order.setVendorIds(new ArrayList<>(vendorIds));
        order=orderRepository.save(order);

        System.out.println("Order Updated with id: "+order.getId());

        sendEmailNotification(order);

        return order;
    }

    private void sendEmailNotification(Order order){
        String customerEmail=(String)customerClient.getCustomerEmailById(order.getCustomerId());
        String Subject="Order Confirmation";
        String message="Your order with id "+order.getId()+" has been placed successfully. Total amount: "+order.getTotalAmount();

        emailSenderService.sendEmail(customerEmail, Subject, message);

       for (String vendorId:order.getVendorIds()) {
           String vendorEmail=(String)vendorClient.getVendorContactMailById(vendorId);
           StringBuilder productDetails=new StringBuilder("OrderDetails\n\n");
           for (OrderItem item:order.getOrderItems()) {
               if (item.getVendorId().equals(vendorId)){
                   productDetails.append("Product Name: ").append(item.getProductName()).append("\n")
                           .append("Quantity: ").append(item.getQuantity()).append("\n\n")
                           .append("Price: ").append(item.getPrice()).append("\n\n");
               }
           }
           String vendorMessage=message+"\n\n"+productDetails.toString();
           emailSenderService.sendEmail(vendorEmail, Subject, vendorMessage);
       }
    }

    public List<Order> getOrderByCustomerId(String id){
        List<Order> orders=orderRepository.findByCustomerId(id);
        if (orders.isEmpty()){
            throw new OrderNotFoundException("Order Not Found");
        }
        return orders;
    }

    public Optional<Order> getOrderById(String id){
        return orderRepository.findById(id);
    }

    public Order updateOrder(String id,Order order){
        if (orderRepository.existsById(id)){
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(String id){
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem updateOrderItem(String id, OrderItem orderItem) {
        if (orderItemRepository.existsById(id)){
            orderItem.setId(id);
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    public boolean deleteOrderItem(String id) {
        if (orderItemRepository.existsById(id)){
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
