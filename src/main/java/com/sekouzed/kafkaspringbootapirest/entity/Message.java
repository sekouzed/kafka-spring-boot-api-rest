package com.sekouzed.kafkaspringbootapirest.entity;

public class Message {

    private String subject;
    private String content;

    public Message( String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{\"subject\":\""+ subject +"\",\"content\":\""+ content +"\"}";
    }
}