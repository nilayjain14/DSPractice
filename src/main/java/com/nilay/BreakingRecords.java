package com.nilay;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BreakingRecords {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        TreeSet<Integer> max = new TreeSet<Integer>();
        TreeSet<Integer> min = new TreeSet<Integer>();

        int baseScore = scores[0];
        for(int i = 1; i<scores.length; i++){
            if(baseScore < scores[i]){
                if(max.size() > 0){
                if(scores[i] > max.last()){
                    max.add(scores[i]);
                }}else{
                    max.add(scores[i]);
                }
            }else{
                if(baseScore > scores[i]){
                    if(min.size() > 0){
                        if(scores[i] < min.first()){
                            min.add(scores[i]);
                        }
                    }else{
                        min.add(scores[i]);
                    }
                }
            }
        }
        System.out.println(max.size()+" "+min.size());

        int[] finalCount = new int[]{max.size(),min.size()};
        return finalCount;
    }


    public static void main(String[] args) throws IOException {


        int[] scores = new int[]{17,45,41,60,17,41,76,43,51,40,89,92,34,6,64,7,37,81,32,50};
        int[] result = breakingRecords(scores);

        System.out.println(result);

    }
}
