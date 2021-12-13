package com.gss.microcollector.task;


import com.gss.microcollector.client.ServiceBClient;
import com.gss.microcollector.entity.Message;
import com.gss.microcollector.repo.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private ServiceBClient client;

    @Autowired
    private MessageRepository messageRepository;

    @Scheduled(fixedRate = 7000)
    public void reportCurrentTime() {
        String allMessage = client.getAllMessage();
        if ("[]".equals(allMessage)) {
            log.info("empty " + allMessage);
        } else {
            save(allMessage);
            log.info("reportCurrentTime " + allMessage);
        }

    }

    private void save(String allMessage) {
        Message message = new Message();
        message.setMessage(allMessage);
        message.setTime(getTime());
        messageRepository.save(message);
    }

    private String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        log.info("Get current time: " + formatDateTime);
        return formatDateTime;
    }
}
