package com.anla.buku.controller;

import com.anla.buku.model.Buku;
import com.anla.buku.service.BukuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/buku")
@RequiredArgsConstructor
public class BukuController {
    
    private final BukuService bukuService;
    
    @GetMapping
    public Map<String, Object> getAllBooks() {
        log.info("GET /api/buku - Fetching all books");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "buku");
        response.put("data", bukuService.findAll());
        return response;
    }
    
    @GetMapping("/{bookId}")
    public ResponseEntity<Object> getBookById(@PathVariable Long bookId) {
        log.info("GET /api/buku/{} - Fetching book by ID", bookId);
        Object result = bukuService.findById(bookId);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Buku createBook(@RequestBody Buku book) {
        log.info("POST /api/buku - Creating new book: {}", book.getJudul());
        return bukuService.save(book);
    }
    
    @PutMapping("/{bookId}")
    public Buku updateBook(@PathVariable Long bookId, @RequestBody Buku book) {
        log.info("PUT /api/buku/{} - Updating book", bookId);
        book.setBookId(bookId);
        return bukuService.update(book);
    }
    
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        log.info("DELETE /api/buku/{} - Deleting book", bookId);
        bukuService.delete(bookId);
    }
}