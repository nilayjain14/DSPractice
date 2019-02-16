package com.nilay;


public class Overloading {

    public static void main(String[] args){

        String s = "nilay";
        System.out.println(s);
        s.concat(" jain");
        //s = s + " jain";
        System.out.println(s);
    }

    private int add(){
        return 0;
    }

    //overloading only meant by parameters: 0 params with diff return type does not work

    /*private String add(){
        return "";
    }*/
}
