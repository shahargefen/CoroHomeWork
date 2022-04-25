package com.example.Coro;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Message {
    @Id
    private String id;
    private Email sender;
    private List<Email> recipients;
    private String subject;
    private String body;
    private long sendTime;

    public Message(Email sender, List<Email> recipients, String subject, String body, long sendTime) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
        this.sendTime = sendTime;
    }
}
