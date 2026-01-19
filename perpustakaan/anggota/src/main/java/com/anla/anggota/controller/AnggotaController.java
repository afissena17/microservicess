package com.anla.anggota.controller;

import com.anla.anggota.model.Anggota;
import com.anla.anggota.service.AnggotaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/anggota")
@RequiredArgsConstructor
public class AnggotaController {
    
    private final AnggotaService anggotaService;
    
    @GetMapping
    public Map<String, Object> getAllMembers() {
        log.info("GET /api/anggota - Fetching all members");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "anggota");
        response.put("data", anggotaService.findAll());
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable Long id) {
        log.info("GET /api/anggota/{} - Fetching member by ID", id);
        Object result = anggotaService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Anggota createMember(@RequestBody Anggota anggota) {
        log.info("POST /api/anggota - Creating new member: {}", anggota.getNama());
        return anggotaService.save(anggota);
    }
    
    @PutMapping("/{id}")
    public Anggota updateMember(@PathVariable Long id, @RequestBody Anggota anggota) {
        log.info("PUT /api/anggota/{} - Updating member", id);
        anggota.setId(id);
        return anggotaService.update(anggota);
    }
    
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        log.info("DELETE /api/anggota/{} - Deleting member", id);
        anggotaService.delete(id);
    }
}