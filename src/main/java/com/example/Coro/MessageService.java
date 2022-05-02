package com.example.Coro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    private  int threadSIZE=300;
    @Autowired
    private CreditCardService creditCardService;



    @Autowired
    private MessageRepository messageRepository;




    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor= new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(threadSIZE);
        return threadPoolTaskExecutor;
    }


    //This function check with CheckMessage function if there is a valid credit card in the message.
    //if there is inserting to the mongoDB.
    //return message of success or failure.
    @Async("threadPoolTaskExecutor")
    public String insertMessage(Message message){
        List<String> detections = creditCardService.CheckCard(message);
        if(!detections.isEmpty()){
            message.setDetections(detections);
            messageRepository.insert(message);
            return "Message inserted";
        }
        else {
            return "Message without credit card";
        }
    }



}
