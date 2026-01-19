package com.anla.Order;

import com.anla.Order.model.Order;
import com.anla.Order.service.CqrsClientService;
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
        Order o1 = new Order(1L, 1L, 1L, 2, 30000000.0, "COMPLETED", LocalDate.now().minusDays(5));
        cqrsClient.save(o1, "1");
        
        Order o2 = new Order(2L, 2L, 2L, 1, 8000000.0, "PENDING", LocalDate.now().minusDays(2));
        cqrsClient.save(o2, "2");
    }
}