package com.nilay;

import java.io.*;

public class GradingStudents {

    static int[] gradingStudents(int[] grades) {
        int[] result = new int[grades.length];
        for(int i=0; i< grades.length; i++){
            result[i] = grades[i];
            for(int j=1; j<=2; j++){
                if( (grades[i]+j)%5 == 0){
                    if((grades[i]+j) >= 40){
                        result[i] = grades[i]+j;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {


        int[] grades = new int[]{73,67,38,33};
        int[] results = gradingStudents(grades);
        for (int result: results){
        System.out.println(result);}

    }
}
