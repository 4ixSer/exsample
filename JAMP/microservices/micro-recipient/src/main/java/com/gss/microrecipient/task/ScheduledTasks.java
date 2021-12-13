package com.gss.microrecipient.task;

import com.gss.microrecipient.entity.MessageStorage;
import com.gss.microrecipient.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class ScheduledTasks {
    @Autowired
    private MessageStorage storage;

    @Autowired
    private AmqpTemplate template;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        String message = (String) template.receiveAndConvert(RabbitConfig.QUEUE_NAME);
        if (message == null) {
            log.info("message is empty");
        } else {
            log.info("message {}", message);
            storage.add(message);
        }
    }
}
