package com.nilay;

import java.util.Arrays;
import java.util.Comparator;

public class FormBiggestNumberFromArray {

    public static void main(String[] args) {
        String[] input = {"54", "546", "548", "60"};
        String[] input1 = {"1", "34", "3", "98", "9", "76", "45", "4"};
        String out = formTheLargestNo(input1);
        System.out.println(out);
    }

    private static String formTheLargestNo(String[] input) {
        String out="";
        Arrays.sort(input, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                String xy = x+y;
                String yx = y+x;

                return xy.compareTo(yx) > 0 ? -1:1;
            }
        });

        for(String l : input){
            out += l;
        }

        return out;
    }
}
