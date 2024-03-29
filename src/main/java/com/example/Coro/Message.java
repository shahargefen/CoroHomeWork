package com.example.Coro;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
public class Message {

    @Id
    private String messageID;
    private String dtoID;
    private String sender;
    private List<String> recipients;
    private String subject;
    private String body;
    private long sendTime;
    private List<String> detections;


    public Message(String dtoID,String sender, List<String> recipients, String subject, String body) {
        this.dtoID=dtoID;
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
        this.sendTime=(Instant.now().toEpochMilli());

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

    public String getMessageID() {
        return messageID;
    }

    public String getDtoID() {
        return dtoID;
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

    public long getSendTime() {
        return sendTime;
    }


    public List<String> getDetections() {
        return detections;
    }

    public void setDetections(List<String> detections) {
        this.detections = detections;
    }
}
