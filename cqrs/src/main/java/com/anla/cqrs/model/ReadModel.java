package com.anla.cqrs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadModel {
    @Id
    private String id;
    
    private String serviceName;
    private String aggregateId;
    private Map<String, Object> data;
    private LocalDateTime lastUpdated;
}