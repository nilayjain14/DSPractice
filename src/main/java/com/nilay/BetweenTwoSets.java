package com.nilay;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class BetweenTwoSets {

    /*
     * Complete the getTotalX function below.
     */
    static int getTotalX(int[] a, int[] b) {
        TreeSet<Integer> a_fact = new TreeSet<Integer>();
        TreeSet<Integer> b_fact = new TreeSet<Integer>();

        int count =0;
        for(int i=a[a.length-1]; i<=b[0]; i++){
            for(int j=0; j<a.length; j++){
                if(i%a[j]==0){
                    count++;
                }
            }
            if(count == a.length){
                a_fact.add(i);
                count=0;
            }else{
                count=0;
            }
        }

        int cnt = 0;
        for(int cur: a_fact){
            for(int j=0; j<b.length; j++){
                if(b[j]%cur==0){
                    cnt++;
                }
            }
            if(cnt==b.length){
                b_fact.add(cur);
                cnt=0;
            }else{
                cnt=0;
            }
        }


        System.out.println(b_fact);
        return b_fact.size();
    }

    public static void main(String[] args) throws IOException {
        //2 4
        //16 32 96

        //3 4
        //24 48


        int[] a = new int[]{3,4};
        int[] b = new int[]{24,48};

        int total = getTotalX(a, b);
        System.out.println(total);
    }
}
