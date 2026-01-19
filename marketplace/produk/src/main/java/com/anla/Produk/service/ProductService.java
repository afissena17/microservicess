package com.anla.Produk.service;

import com.anla.Produk.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final CqrsClientService cqrsClient;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public Product save(Product product) {
        product.setId(idCounter.getAndIncrement());
        cqrsClient.save(product, product.getId().toString());
        return product;
    }
    
    public Product update(Product product) {
        cqrsClient.update(product, product.getId().toString());
        return product;
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