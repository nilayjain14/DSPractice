package com.nilay;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {

    public static void main(String[] args) {
        final String input = "geeksforgeeks";
        char res = findOutFirstNonRepeating(input);
        System.out.println(res);
    }

    private static char findOutFirstNonRepeating(String input) {

        char[] a = input.toCharArray();
        LinkedHashMap<Character,Integer> res = new LinkedHashMap<>();
        for(char val : a){
            if(res.containsKey(val)){
                int count = res.get(val);
                res.put(val,++count);
            }else{
                res.put(val,1);
            }
        }

        for (Map.Entry<Character,Integer> entry : res.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return 'a';
    }
}
