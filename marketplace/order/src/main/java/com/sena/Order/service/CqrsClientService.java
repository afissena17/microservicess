package com.sena.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CqrsClientService {
    
    @Value("${cqrs.service.url}")
    private String cqrsUrl;
    
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    
    public void save(Object data, String entityId) {
        rabbitTemplate.convertAndSend("cqrs.command.queue", Map.of(
            "serviceName", "order",
            "aggregateId", entityId,
            "eventType", "CREATE",
            "eventData", toJson(data),
            "timestamp", LocalDateTime.now().toString(),
            "version", 1L
        ));
    }
    
    public void update(Object data, String entityId) {
        rabbitTemplate.convertAndSend("cqrs.command.queue", Map.of(
            "serviceName", "order",
            "aggregateId", entityId,
            "eventType", "UPDATE",
            "eventData", toJson(data),
            "timestamp", LocalDateTime.now().toString(),
            "version", 1L
        ));
    }
    
    public void delete(String entityId) {
        rabbitTemplate.convertAndSend("cqrs.command.queue", Map.of(
            "serviceName", "order",
            "aggregateId", entityId,
            "eventType", "DELETE",
            "eventData", "{\"deleted\":true}",
            "timestamp", LocalDateTime.now().toString(),
            "version", 1L
        ));
    }
    
    public Object findById(String entityId) {
        try {
            return restTemplate.getForObject(
                cqrsUrl + "/api/cqrs/order/query/" + entityId, Map.class);
        } catch (Exception e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Object> findAll() {
        try {
            List<Object> result = restTemplate.getForObject(cqrsUrl + "/api/cqrs/order/query", List.class);
            return result != null ? result : List.of();
        } catch (Exception e) {
            return List.of();
        }
    }
    
    private String toJson(Object data) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(data);
        } catch (Exception e) {
            return "{}";
        }
    }
}
