package com.nilay;

import java.io.IOException;

public class BirthdayChocolate {

    static int solve(int[] s, int d, int m){

        int temp = 0;
        int cnt = 0;
        int index = 0;
        for(int i=0; i<s.length; i++){
            temp = s[i];
            int j = i+1;
            while(index>0){
                temp = temp + s[j];
                j++;
                index--;
            }
            if(temp == d){
                cnt++;
            }
            index = m;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {

        int[] s = new int[]{2,5,1,3,4,4,3,5,1,1,2,1,4,1,3,3,4,2,1};
        int d,m;
        int result = solve(s, 18, 7);
        System.out.println(result);

    }
}
