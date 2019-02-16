package com.nilay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contains {

    public static void main(String[] args){

        String[] haha = {"nilay","ved","kanchan","nilofar"};
        List<String> list = Arrays.asList(haha);


//        list.add("nilay");
//        list.add("ved");
//        list.add("kanchan");
//        list.add("nilofar");

        //list.add(Arrays.asList(haha));

        System.out.println(haha.toString());

        boolean check = list.contains("nilay");
        System.out.println("check for nilay = " + check);

        boolean check1 = list.contains("lol");
        System.out.println("check for lol = " + check1);

    }
}
