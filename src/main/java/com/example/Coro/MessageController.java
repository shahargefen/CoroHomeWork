package com.example.Coro;

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
    public String postMessage(@RequestBody Message message){

        return messageService.insertMessage(message);

    }
}
