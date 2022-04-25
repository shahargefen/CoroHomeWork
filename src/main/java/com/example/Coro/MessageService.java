package com.example.Coro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean CheckMessage(Message message){
        return true;
    }


}
