package com.gss.microrecipient.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageStorage {

    private List<String> db;

    public MessageStorage() {
        db = new ArrayList<>();
    }

    public void add(String value) {
        db.add(value);
    }

    public List<String> getMessage() {
        return db;
    }

    public void clean() {
        db = new ArrayList<>();
    }
}
