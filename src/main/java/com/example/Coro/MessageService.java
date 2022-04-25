package com.example.Coro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public String insertMessage(Message message){
        if(CheckMessage(message)){
            messageRepository.insert(message);
            return "Message inserted";
        }
        else {
            return "Message without credit card";
        }
    }

    public boolean CheckMessage(Message message) {
        String body = message.getBody().toLowerCase();
        String subject = message.getSubject().toLowerCase();
        String ccPattern = "(?<!\\d)\\d{16}(?!\\d)|(?<!\\d)\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}(?!\\d)|(?<!\\d)\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}(?!\\d)";
        Pattern pattern = Pattern.compile(ccPattern);
        Matcher matcherBody = pattern.matcher(body);
        Matcher matcherSubject = pattern.matcher(subject);

        if (subject.contains("creditcard") || subject.contains("credit card") || matcherSubject.find()){
            return true;
        }
        if (body.contains("creditcard") || body.contains("credit card") || matcherBody.find()) {
            return true;
        } else {
            return false;
        }
    }





}
