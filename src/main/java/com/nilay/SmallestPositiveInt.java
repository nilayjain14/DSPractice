package com.nilay;

import java.util.*;

public class SmallestPositiveInt {

    public static void main(String[] args){
        int[] arr = {1,4,-1,3,2};
        int ele = findSmallestPositive(arr);
        System.out.println(ele);
    }

    private static int findSmallestPositive(int[] arr){
        int counter = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i : arr)
        {
            list.add(i);
        }
        TreeSet<Integer> set = new TreeSet<Integer>(list);
        Iterator<Integer> it = set.iterator();
        Integer current = 0;
        while(it.hasNext() ) {
            current = it.next();
            if(current != counter){
                return counter;
            }
            counter++;
        }

        return counter;
    }
}
