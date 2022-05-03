package com.example.Coro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MessageController {


    @Autowired
    private MessageService messageService;


    @PostMapping("/message")
    public String postMessage(@RequestBody MessageDTO messageDTO){
        Message message=new Message(messageDTO.getId(),messageDTO.getSender(),messageDTO.getRecipients(),messageDTO.getSubject(),messageDTO.getBody());
        return listen(message);

    }

    @RabbitListener(queues = "Message-queue")
    public String listen(Message message) {
        return messageService.insertMessage(message);

    }
}
