package com.gss.microsender.controller;

import com.gss.microsender.entity.IncMessage;
import com.gss.microsender.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@Slf4j
public class NotificatorController {

    @Autowired
    private AmqpTemplate template;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void notify(@RequestBody IncMessage message) {
        log.info("Message = {}", message);
        template.convertAndSend(RabbitConfig.QUEUE_NAME, message.getMessage());
        log.info("Added Message= {}", message.getMessage());
    }
}