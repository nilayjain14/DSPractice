package com.nilay;

/**
 * Created by jainn on 10/2/17.
 */
public class SplitByPipe {

    public static void main(String[] args){
        String temp = "Nilay-Salil-Saurabh";
        String[] temp1 = temp.split("-");

        System.out.println(temp1[1]);
    }
}
