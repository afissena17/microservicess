package com.anla.Pengembalian.service;

import com.anla.Pengembalian.model.Pengembalian;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PengembalianService {
    
    private final CqrsClientService cqrsClient;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public Pengembalian save(Pengembalian pengembalian) {
        pengembalian.setId(idCounter.getAndIncrement());
        cqrsClient.save(pengembalian, pengembalian.getId().toString());
        return pengembalian;
    }
    
    public Pengembalian update(Pengembalian pengembalian) {
        cqrsClient.update(pengembalian, pengembalian.getId().toString());
        return pengembalian;
    }
    
    public void delete(Long id) {
        cqrsClient.delete(id.toString());
    }
    
    public Object findById(Long id) {
        return cqrsClient.findById(id.toString());
    }
    
    public List<Object> findAll() {
        return cqrsClient.findAll();
    }
}