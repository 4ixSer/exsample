package com.gss.microvisualizer.controller;

import com.gss.microvisualizer.entity.Message;
import com.gss.microvisualizer.repo.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saved-messages")
@Slf4j
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @GetMapping
    public Iterable<Message> getMessage() {
        List<Message> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        log.info("Messages :{}", result.size());
        return result;
    }
}