package com.sena.Peminjaman.controller;

import com.sena.Peminjaman.VO.ResponseTemplateVO;
import com.sena.Peminjaman.dto.PeminjamanDto;
import com.sena.Peminjaman.model.Peminjaman;
import com.sena.Peminjaman.service.PeminjamanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/peminjaman")
@RequiredArgsConstructor
public class PeminjamanController {

    private final PeminjamanService service;

    @GetMapping
    public Map<String, Object> getAllPeminjaman() {
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "peminjaman");
        response.put("data", service.getAllPeminjaman());
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeminjamanById(@PathVariable Long id) {
        Object result = service.getPeminjamanById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/denda/{id}")
    public ResponseEntity<PeminjamanDto> getPeminjamanWithDenda(@PathVariable Long id) {
        PeminjamanDto result = service.getPeminjamanWithDenda(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman) {
        return service.createPeminjaman(peminjaman);
    }

    @PutMapping("/{id}")
    public Peminjaman updatePeminjaman(@PathVariable Long id, @RequestBody Peminjaman peminjaman) {
        return service.updatePeminjaman(id, peminjaman);
    }

    @DeleteMapping("/{id}")
    public void deletePeminjaman(@PathVariable Long id) {
        service.deletePeminjaman(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseTemplateVO> getPeminjamanWithDetailById(@PathVariable Long id) {
        ResponseTemplateVO result = service.getPeminjamanWithDetailById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
