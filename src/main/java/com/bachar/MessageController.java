package com.bachar;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/messages")
@AllArgsConstructor
public class MessageController {

private final KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        Message message = new Message(request.message(), LocalDateTime.now());
        kafkaTemplate.send("bachar", message);
    }
}
