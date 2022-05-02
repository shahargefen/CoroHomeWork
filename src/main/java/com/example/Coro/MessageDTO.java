package com.example.Coro;

import org.springframework.data.annotation.Id;

import java.util.List;

public class MessageDTO {
    @Id
    private String id;
    private String sender;
    private List<String> recipients;
    private String subject;
    private String body;
    private long sendTime;


    public MessageDTO(String sender, List<String> recipients, String subject, String body) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public long getSendTime() {
        return sendTime;
    }
}
