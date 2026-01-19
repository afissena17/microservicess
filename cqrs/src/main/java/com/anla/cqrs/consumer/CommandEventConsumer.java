package com.anla.cqrs.consumer;

import com.anla.cqrs.event.Event;
import com.anla.cqrs.service.EventStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandEventConsumer {
    
    private final EventStoreService eventStoreService;
    
    @RabbitListener(queues = "cqrs.command.queue")
    public void handleCommand(Event event) {
        log.info("Received command from service: {} for aggregate: {}", event.getServiceName(), event.getAggregateId());
        
        try {
            eventStoreService.saveEvent(event);
            log.info("Command processed successfully for aggregate: {}", event.getAggregateId());
        } catch (Exception e) {
            log.error("Error processing command: {}", e.getMessage(), e);
        }
    }
}
