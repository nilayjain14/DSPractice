package com.nilay;



import java.util.*;

public class MinMax {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {

        TreeSet<Long> a = new TreeSet<Long>();
        Long temp = 0L;
        for(int j=0; j<arr.length; j++){
            temp = temp + arr[j];
        }

        for(int i=0; i<arr.length; i++){
            a.add(temp - arr[i]);
        }

        System.out.println(a.first() + " " + a.last());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}
