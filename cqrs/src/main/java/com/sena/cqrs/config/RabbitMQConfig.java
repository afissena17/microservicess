package com.sena.cqrs.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String CQRS_EVENT_QUEUE = "cqrs.event.queue";
    public static final String CQRS_COMMAND_QUEUE = "cqrs.command.queue";
    
    @Bean
    public Queue cqrsEventQueue() {
        return new Queue(CQRS_EVENT_QUEUE, true);
    }
    
    @Bean
    public Queue cqrsCommandQueue() {
        return new Queue(CQRS_COMMAND_QUEUE, true);
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
