package com.sena.cqrs.consumer;

import com.sena.cqrs.config.RabbitMQConfig;
import com.sena.cqrs.event.Event;
import com.sena.cqrs.model.ReadModel;
import com.sena.cqrs.service.ReadModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {
    
    private final ReadModelService readModelService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @RabbitListener(queues = RabbitMQConfig.CQRS_EVENT_QUEUE)
    public void handleEvent(Event event) {
        log.info("Consuming event: {} for aggregate: {}", event.getEventType(), event.getAggregateId());
        
        try {
            switch (event.getEventType()) {
                case "CREATE", "UPDATE" -> {
                    ReadModel readModel = new ReadModel();
                    readModel.setServiceName(event.getServiceName());
                    readModel.setAggregateId(event.getAggregateId());
                    readModel.setData(parseEventData(event.getEventData()));
                    readModel.setLastUpdated(LocalDateTime.now());
                    readModelService.saveReadModel(readModel);
                    log.info("Read model updated for aggregate: {}", event.getAggregateId());
                }
                case "DELETE" -> {
                    readModelService.deleteReadModel(event.getServiceName(), event.getAggregateId());
                    log.info("Read model deleted for aggregate: {}", event.getAggregateId());
                }
                default -> log.warn("Unknown event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage(), e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> parseEventData(String eventData) {
        try {
            return objectMapper.readValue(eventData, Map.class);
        } catch (Exception e) {
            log.error("Error parsing event data: {}", e.getMessage());
            return Map.of();
        }
    }
}
