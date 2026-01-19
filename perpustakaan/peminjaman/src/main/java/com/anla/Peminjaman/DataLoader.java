package com.anla.Peminjaman;

import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.service.CqrsClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        Peminjaman p = new Peminjaman(1L, LocalDate.now(), LocalDate.now().plusDays(10), LocalDate.now().plusDays(7), 1L, 1L);
        cqrsClient.save(p, "1");
    }
}
