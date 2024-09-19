package com.example.orderservice.service;

import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> getOrderItemById(String id) {
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public boolean deleteOrderItem(String id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OrderItem updateOrderItem(String id, OrderItem orderItem) {
        if (orderItemRepository.existsById(id)) {
            orderItem.setId(id);
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public List<OrderItem> getOrderItemsByProductId(String productId) {
        return orderItemRepository.findByProductId(productId);
    }

    public List<OrderItem> getOrderItemsByVendorId(String vendorId) {
        return orderItemRepository.findByVendorId(vendorId);
    }
}
