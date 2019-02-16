package com.nilay;

import java.io.*;

public class Kangaroo {

    // Complete the kangaroo function below.
    static String kangaroo(int x1, int v1, int x2, int v2) {
//        if((x2>x1 && v2>v1) || v1==v2){
//            return "NO";
//        }else if((x1>x2 && v1>v2) || v1==v2){
//            return "NO";
//        }else{
//            return "YES";
//        }
        int k1 = x1;
        int k2 = x2;
        if(x2>x1&&v2>v1){
            return "NO";
        }else{
            for(int i=0;i<10000;i++){
                k1+=v1;
                k2+=v2;
                if(k1==k2){
                    return "YES";
                }
            }
            return "NO";
        }

    }

    public static void main(String[] args) throws IOException {
        // x1, v1, x2, v2

        String result = kangaroo(21, 6, 47, 3);
        System.out.println(result);

    }
}
