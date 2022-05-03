package com.example.Coro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication

public class CoroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoroApplication.class, args);
		//SpringApplication.run(MessagingRabbitmq.class,args);
	}

}
