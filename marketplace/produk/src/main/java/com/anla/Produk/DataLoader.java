package com.anla.Produk;

import com.anla.Produk.model.Product;
import com.anla.Produk.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        cqrsClient.save(new Product(1L, "Laptop", "Unit", "15000000"), "1");
    }
}
