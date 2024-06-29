package com.bachar;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "bachar", groupId = "bacharGroupId", containerFactory = "factory")
    void listener(Message data) {
        System.out.println("Listener received: " + data.message() + " from bachar");
    }
}
