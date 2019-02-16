package com.nilay;

import java.util.Scanner;

/**
 * Created by jainn on 8/4/17.
 */
public class IPValidator {

    private static final String zeroTo255 = "([0-9]|[0-9][0-9]|(0|1)[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    private static final String pattern = zeroTo255 + "." + zeroTo255 + "." + zeroTo255 + "." + zeroTo255;
    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(pattern));
        }
    }
}
