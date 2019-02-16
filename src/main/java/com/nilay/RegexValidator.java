package com.nilay;


import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by jainn on 8/4/17.
 */
public class RegexValidator {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String pattern = in.nextLine();
            try{
                Pattern.compile(pattern);
                System.out.println("Valid");
            }
            catch (PatternSyntaxException p){
                System.out.println("Invalid");
            }

        }
    }
}
