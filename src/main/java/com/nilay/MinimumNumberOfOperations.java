package com.nilay;

import java.io.*;
import java.util.*;

public class MinimumNumberOfOperations {

    // Complete the equal function below.
    static long journey(List<Integer> path, int k) {

        Long maxSum = 0L;
        int i = 0;
        int fake= 0;
        int count = k;
        do{
            if(fake == 0){
            TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>();
            for(int j=i+1; j<=k; j++){
                temp.put(path.get(i) + path.get(j),j);
            }
            maxSum = maxSum + temp.lastKey();
            i = temp.get(temp.lastKey());
            fake =1;
            }else{
                TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>();
                int j = i+1;
                while(count > 0){
                    temp.put(maxSum.intValue() + path.get(j),j);
                    count--;
                    j++;
                }
                maxSum = temp.lastKey().longValue();
                i = temp.get(temp.lastKey());
            }
            count = k;
        }while(i < path.size() - 1 );

        return maxSum;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

            ArrayList<Integer> path = new ArrayList<Integer>();
            //10 -20 -5
            //100 -70 -90 -80 100
            path.add(100);
            path.add(-70);
            path.add(-90);
            path.add(-80);
            path.add(100);

//        path.add(10);
//        path.add(-20);
//        path.add(-5);
            long result = journey(path, 3);
            System.out.println(result);

    }
}
