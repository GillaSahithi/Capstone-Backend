package com.example.orderservice.controller;

import com.example.orderservice.model.OrderItem;
import com.example.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable String id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        if (orderItem.isPresent()) {
            return ResponseEntity.ok(orderItem.get());
        }
        return ResponseEntity.status(404).body("Order item not found with id: "+ id);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderItemByOrderId(@PathVariable String orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getOrderItemByProductId(@PathVariable String productId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByProductId(productId);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getOrderItemByVendorId(@PathVariable String vendorId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByVendorId(vendorId);
        return ResponseEntity.ok(orderItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable String id, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
        if(updatedOrderItem != null) {
            return ResponseEntity.ok(updatedOrderItem);
        }
        return ResponseEntity.status(404).body("Order item not found with id: "+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable String id) {
        boolean deleted = orderItemService.deleteOrderItem(id);
        if(deleted) {
            return ResponseEntity.ok("Order item deleted successfully");
        }
        return ResponseEntity.status(404).body("Order item not found with id: "+ id);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }
}
