package com.nilay;

public class StartNew {

    public static void main(String[] args) {
        //int[] arr = {1, 4, 5, 6, 2, 3,-1};

        int[] arr = {1,4,-1,3,2};

        int ele = findLengh(arr);
        System.out.println(ele);
    }

    private static int findLengh(int[] arr) {
        int i = 0, value = 0;
        int count = 1;
        while (arr[i] != -1) {
            value = arr[i];
            i=value;
            count++;
        }
        return count;
    }

}
