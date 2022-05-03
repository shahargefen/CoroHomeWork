package com.example.Coro;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private int count=300;
    private CountDownLatch latch = new CountDownLatch(count);

    public void receiveMessage() {
        latch.countDown();

    }

    public CountDownLatch getLatch() {
        return latch;
    }

}