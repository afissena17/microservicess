package com.sena.pelanggan;

import com.sena.pelanggan.model.Pelanggan;
import com.sena.pelanggan.service.CqrsClientService;
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
        cqrsClient.save(new Pelanggan(1L, "P001", "John Doe", "Jakarta"), "1");
    }
}
