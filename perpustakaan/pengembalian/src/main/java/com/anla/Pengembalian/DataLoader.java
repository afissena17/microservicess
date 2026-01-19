package com.anla.Pengembalian;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        cqrsClient.save(new Pengembalian(1L, LocalDate.now(), 0, BigDecimal.ZERO, 1L), "1");
    }
}