package com.anla.cqrs.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String serviceName;
    private String aggregateId;
    private String eventType;
    
    @Column(columnDefinition = "TEXT")
    private String eventData;
    
    private LocalDateTime timestamp;
    private Long version;
}