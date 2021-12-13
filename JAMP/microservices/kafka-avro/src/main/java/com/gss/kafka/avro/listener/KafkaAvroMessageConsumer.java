package com.gss.kafka.avro.listener;

import com.gss.kafka.avro.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroMessageConsumer {

    @KafkaListener(topics = "shine-test-local-avro-topic", groupId = "shine-local-avro")
    public void listen(Message message) {
        log.info("Received Messasge: {}", message);
    }
}
