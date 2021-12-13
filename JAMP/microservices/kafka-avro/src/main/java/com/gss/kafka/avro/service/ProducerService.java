package com.gss.kafka.avro.service;

import com.gss.kafka.avro.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class ProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(
                getListenableFutureCallback(message));
    }

    private ListenableFutureCallback<SendResult<String, Message>> getListenableFutureCallback(Message message) {
        return new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onSuccess(SendResult<String, Message> result) {
                log.info(
                        "Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }
        };
    }
}