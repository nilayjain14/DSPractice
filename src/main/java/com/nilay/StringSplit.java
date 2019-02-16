package com.nilay;

import java.util.Scanner;

/**
 * Created by jainn on 8/4/17.
 */
public class StringSplit {

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            int count = 0;
            // Write your code here.
            String[] splitedOutput = s.split(" |\\,|\\'|\\?|\\!|\\.|\\@|\\_");
            for(String current : splitedOutput) {
                if(!current.equals("")){
                    count++;
                    System.out.println(current);
                }
            }
            System.out.println(count);
            for(String current : splitedOutput) {
                if(!current.equals("")){
                    System.out.println(current);
                }
            }
            scan.close();
        }
}
