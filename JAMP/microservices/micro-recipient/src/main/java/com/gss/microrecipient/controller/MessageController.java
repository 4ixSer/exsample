package com.gss.microrecipient.controller;

import com.gss.microrecipient.entity.MessageStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    private MessageStorage storage;

    @GetMapping
    public List<String> getMessage() {
        log.info("MessageController.getMessage");
        List<String> storageMessage = storage.getMessage();
        storage.clean();
        log.info("clean");
        return storageMessage;
    }
}
