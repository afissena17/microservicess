package com.sena.anggota;

import com.sena.anggota.model.Anggota;
import com.sena.anggota.service.CqrsClientService;
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
        cqrsClient.save(new Anggota(1L, "2311083015", "sena", "Padang", "Laki-laki", "senaharpanda@gmail.com"), "1");
    }
}