package com.sena.buku;

import com.sena.buku.model.Buku;
import com.sena.buku.service.CqrsClientService;
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
        cqrsClient.save(new Buku(1L, "Java Programming", "John Doe", "Politeknik Negeri Padang", 2023), "1");
    }
}