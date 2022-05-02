package com.example.Coro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping
public class MessageController {


    @Autowired
    private MessageService messageService;


    @PostMapping("/message")
    public String postMessage(@RequestBody MessageDTO messageDTO){
        Message message=new Message(messageDTO.getId(),messageDTO.getSender(),messageDTO.getRecipients(),messageDTO.getSubject(),messageDTO.getBody());

        return messageService.insertMessage(message);

    }
}
