package com.example.Coro;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreditCardService {
    //The function get message and check in the body and the subject if there is a valid credit card number.
    //The function return list of the valid credit cards.
    public List<String> CheckCard(Message message) {
        List<String> allMatches = new ArrayList<String>();
        String body = message.getBody().toLowerCase();
        String subject = message.getSubject().toLowerCase();
        //check 3 formats of credit numbers
        //1 - 16 numbers in a row example 1234567890123456
        //2 - 4 times of 4 number with space between them example 1234 1234 1234 1234
        //3 - 4 times of 4 numbers with '-' between them example 1234-1234-1234-1234
        String ccPattern = "(?<!\\d)\\d{16}(?!\\d)|(?<!\\d)\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}(?!\\d)|(?<!\\d)\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}(?!\\d)";
        Pattern pattern = Pattern.compile(ccPattern);
        Matcher matcherBody = pattern.matcher(body);
        Matcher matcherSubject = pattern.matcher(subject);

        //find all the credit card in the subject message of the regex pattern.
        while(matcherSubject.find()){
            String creditNumSubject=matcherSubject.group();
            if(LuhnAlgorithm(creditNumSubject)) {
                allMatches.add(creditNumSubject);
            }
        }

        //find all the credit card in the body message of the regex pattern.
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
        //check if the credit card is in 0000-0000-0000-0000 format
        if(cardNumber.contains("-")) {
            String[] splitCardNumber = cardNumber.split("-", 5);
            cardNumber="";
            for(int i=0;i<splitCardNumber.length;i++){
                cardNumber=cardNumber+splitCardNumber[i];
            }
        }

        //check if the credit card is in 0000 0000 0000 0000 format
        if(cardNumber.contains(" ")) {
            String[] splitCardNumber = cardNumber.split(" ", 5);
            cardNumber="";
            for(int i=0;i<splitCardNumber.length;i++){
                cardNumber=cardNumber+splitCardNumber[i];
            }
        }

        // int array for processing the cardNumber
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
