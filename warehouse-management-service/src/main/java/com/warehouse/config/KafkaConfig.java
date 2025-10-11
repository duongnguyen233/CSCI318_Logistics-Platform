package com.warehouse.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic inventoryTopic() {
        // Auto-create the topic 'inventory-events' if it doesn’t exist
        return TopicBuilder.name("inventory-events").build();
    }
}
