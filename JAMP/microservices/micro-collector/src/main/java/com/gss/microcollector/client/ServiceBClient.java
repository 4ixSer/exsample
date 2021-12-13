package com.gss.microcollector.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "micro-recipient", url = "micro-recipient:9001")
public interface ServiceBClient {
    @GetMapping("/message")
    String getAllMessage();
}
