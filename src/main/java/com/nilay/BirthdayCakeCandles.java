package com.nilay;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BirthdayCakeCandles {

    // Complete the birthdayCakeCandles function below.
    static int birthdayCakeCandles(int[] ar) {

        TreeMap<Integer,Integer> candleHeightAndCount = new TreeMap<Integer,Integer>();
        int occurrences = 1;
        for(int i = 0 ; i < ar.length ; i++){
            if(candleHeightAndCount.containsKey(ar[i])){
                int candleCount = candleHeightAndCount.get(ar[i]);
                candleCount ++;
                candleHeightAndCount.put(ar[i],candleCount);
            }else{
                candleHeightAndCount.put(ar[i],occurrences);
            }
        }
        return candleHeightAndCount.get(candleHeightAndCount.lastKey());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < arCount; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = birthdayCakeCandles(ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
