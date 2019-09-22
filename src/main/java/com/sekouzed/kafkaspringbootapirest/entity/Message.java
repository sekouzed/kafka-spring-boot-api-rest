package com.sekouzed.kafkaspringbootapirest.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {

    private String subject;
    private String content;

    public Message( String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{\"subject\":\""+ subject +"\",\"content\":\""+ content +"\"}";
    }
}