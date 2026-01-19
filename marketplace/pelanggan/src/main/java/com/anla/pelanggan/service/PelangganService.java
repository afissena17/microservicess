package com.anla.pelanggan.service;

import com.anla.pelanggan.model.Pelanggan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PelangganService {

    private final CqrsClientService cqrsClient;
    private final AtomicLong idCounter = new AtomicLong(1);

    public Pelanggan save(Pelanggan pelanggan) {
        pelanggan.setId(idCounter.getAndIncrement());
        cqrsClient.save(pelanggan, pelanggan.getId().toString());
        return pelanggan;
    }

    public Pelanggan update(Pelanggan pelanggan) {
        cqrsClient.update(pelanggan, pelanggan.getId().toString());
        return pelanggan;
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