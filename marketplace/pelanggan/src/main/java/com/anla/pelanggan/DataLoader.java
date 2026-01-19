package com.anla.pelanggan;

import com.anla.pelanggan.model.Pelanggan;
import com.anla.pelanggan.service.CqrsClientService;
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
