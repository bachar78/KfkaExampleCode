package com.bachar.config;

import com.bachar.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //Config to pass to producer factory. Should be in form Map data structure
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return props;
    }


    //Consumer factory: responsible for creating consumer instances
    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        JsonDeserializer<Message> jsonDeserializer = new JsonDeserializer<>(Message.class);
        jsonDeserializer.addTrustedPackages("com.bachar");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    //Listener to  receive all messages from topics partition from single thread
    @Bean
    public KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<String, Message>> factory(
            ConsumerFactory<String, Message> consumerFactory
    ) {
            ConcurrentKafkaListenerContainerFactory<String, Message> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            return factory;
    }

}
