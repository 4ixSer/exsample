package com.gss.kafka.avro.controller;

import com.gss.kafka.avro.model.Message;
import com.gss.kafka.avro.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    @Autowired
    ProducerService producerService;

    @PostMapping(value = "/avro/Message")
    public String kafkaStudent(@RequestBody Message message) {
        log.info("message = {}", message);
        producerService.sendMessage(message);
        return "Success";
    }
}