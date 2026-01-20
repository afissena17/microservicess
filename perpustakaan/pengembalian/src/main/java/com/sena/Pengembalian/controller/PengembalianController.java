package com.sena.Pengembalian.controller;

import com.sena.Pengembalian.model.Pengembalian;
import com.sena.Pengembalian.service.PengembalianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/pengembalian")
@RequiredArgsConstructor
public class PengembalianController {
    
    private final PengembalianService pengembalianService;
    
    @GetMapping
    public Map<String, Object> getAllReturns() {
        log.info("GET /api/pengembalian - Fetching all returns");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "pengembalian");
        response.put("data", pengembalianService.findAll());
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getReturnById(@PathVariable Long id) {
        log.info("GET /api/pengembalian/{} - Fetching return by ID", id);
        Object result = pengembalianService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Pengembalian createReturn(@RequestBody Pengembalian pengembalian) {
        log.info("POST /api/pengembalian - Creating new return for peminjaman ID: {}", pengembalian.getPeminjamanId());
        return pengembalianService.save(pengembalian);
    }
    
    @PutMapping("/{id}")
    public Pengembalian updateReturn(@PathVariable Long id, @RequestBody Pengembalian pengembalian) {
        log.info("PUT /api/pengembalian/{} - Updating return", id);
        pengembalian.setId(id);
        return pengembalianService.update(pengembalian);
    }
    
    @DeleteMapping("/{id}")
    public void deleteReturn(@PathVariable Long id) {
        log.info("DELETE /api/pengembalian/{} - Deleting return", id);
        pengembalianService.delete(id);
    }
}