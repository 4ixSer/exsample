package com.gss.microsender.entity;

import lombok.Data;

@Data
public class IncMessage {

    private String user;
    private String message;

    public IncMessage() {
    }

    public IncMessage(String user, String message) {
        this.user = user;
        this.message = message;
    }
}
