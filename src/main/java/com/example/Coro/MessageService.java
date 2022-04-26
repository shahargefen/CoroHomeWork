package com.example.Coro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public String insertMessage(Message message){
        List<String> detections = CheckMessage(message);
        if(!detections.isEmpty()){
            message.setSendTime(Instant.now().toEpochMilli());
            message.setDetections(detections);
            messageRepository.insert(message);
            return "Message inserted";
        }
        else {
            return "Message without credit card";
        }
    }

    public List<String> CheckMessage(Message message) {
        List<String> allMatches = new ArrayList<String>();
        String body = message.getBody().toLowerCase();
        String subject = message.getSubject().toLowerCase();
        String ccPattern = "(?<!\\d)\\d{16}(?!\\d)|(?<!\\d)\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}(?!\\d)|(?<!\\d)\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}(?!\\d)";
        Pattern pattern = Pattern.compile(ccPattern);
        Matcher matcherBody = pattern.matcher(body);
        Matcher matcherSubject = pattern.matcher(subject);

        while(matcherSubject.find()){
            String creditNumSubject=matcherSubject.group();
            if(LuhnAlgorithm(creditNumSubject)) {
                allMatches.add(creditNumSubject);
            }
        }
        while(matcherBody.find()){
            String creditNumBody=matcherBody.group();
            if(LuhnAlgorithm(creditNumBody))
                {
                    allMatches.add(creditNumBody);
                }
        }
        return allMatches;


    }

    public static boolean LuhnAlgorithm(String cardNumber)
    {
        // int array for processing the cardNumber
        if(cardNumber.contains("-")) {
            String[] splitCardNumber = cardNumber.split("-", 5);
            cardNumber="";
            for(int i=0;i<splitCardNumber.length;i++){
                cardNumber=cardNumber+splitCardNumber[i];
            }
        }
        if(cardNumber.contains(" ")) {
            String[] splitCardNumber = cardNumber.split(" ", 5);
            cardNumber="";
            for(int i=0;i<splitCardNumber.length;i++){
                cardNumber=cardNumber+splitCardNumber[i];
            }
        }

        int[] cardIntArray=new int[cardNumber.length()];

        for(int i=0;i<cardNumber.length();i++)
        {
            char c= cardNumber.charAt(i);
            cardIntArray[i]=  Integer.parseInt(""+c);
        }

        for(int i=cardIntArray.length-2;i>=0;i=i-2)
        {
            int num = cardIntArray[i];
            num = num * 2;  // step 1
            if(num>9)
            {
                num = num%10 + num/10;  // step 2
            }
            cardIntArray[i]=num;
        }

        int sum = sumDigits(cardIntArray);  // step 3

        if(sum%10==0)  // step 4
        {
            return true;
        }

        return false;

    }

    public static int sumDigits(int[] arr)
    {
        return Arrays.stream(arr).sum();
    }


}
