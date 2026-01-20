package com.sena.pelanggan.controller;

import com.sena.pelanggan.model.Pelanggan;
import com.sena.pelanggan.service.PelangganService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/pelanggan")
@RequiredArgsConstructor
public class PelangganController {
    
    private final PelangganService pelangganService;
    
    @GetMapping
    public Map<String, Object> getAllCustomers() {
        log.info("GET /api/pelanggan - Fetching all customers");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "pelanggan");
        response.put("data", pelangganService.findAll());
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        log.info("GET /api/pelanggan/{} - Fetching customer by ID", id);
        Object result = pelangganService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Pelanggan createCustomer(@RequestBody Pelanggan pelanggan) {
        log.info("POST /api/pelanggan - Creating new customer: {}", pelanggan.getNama());
        return pelangganService.save(pelanggan);
    }
    
    @PutMapping("/{id}")
    public Pelanggan updateCustomer(@PathVariable Long id, @RequestBody Pelanggan pelanggan) {
        log.info("PUT /api/pelanggan/{} - Updating customer", id);
        pelanggan.setId(id);
        return pelangganService.update(pelanggan);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        log.info("DELETE /api/pelanggan/{} - Deleting customer", id);
        pelangganService.delete(id);
    }
}