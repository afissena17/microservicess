package com.sena.Order.controller;

import com.sena.Order.model.Order;
import com.sena.Order.service.OrderService;
import com.sena.Order.VO.ResponseTemplateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService service;
    
    @GetMapping
    public Map<String, Object> getAllOrders() {
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "order");
        response.put("data", service.getAllOrders());
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
        Object result = service.getOrderById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseTemplateVO> getOrderWithDetailById(@PathVariable Long id) {
        ResponseTemplateVO result = service.getOrderWithDetailById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }
    
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}