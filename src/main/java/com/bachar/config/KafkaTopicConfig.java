package com.bachar.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//This class is responsible for creating topics
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic bacharTopic() {
        return TopicBuilder.name("bachar").build();
    }


}
