package com.sena.anggota.service;

import com.sena.anggota.model.Anggota;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AnggotaService {
    
    private final CqrsClientService cqrsClient;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public Anggota save(Anggota anggota) {
        anggota.setId(idCounter.getAndIncrement());
        cqrsClient.save(anggota, anggota.getId().toString());
        return anggota;
    }
    
    public Anggota update(Anggota anggota) {
        cqrsClient.update(anggota, anggota.getId().toString());
        return anggota;
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