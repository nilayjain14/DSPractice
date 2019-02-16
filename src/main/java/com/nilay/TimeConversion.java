package com.nilay;

import java.io.*;
import java.util.*;

public class TimeConversion {

    static String timeConversionMethod(String s) {
        int index=0;
        String[] time_12 = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] time_24 = new String[]{"13","14","15","16","17","18","19","20","21","22","23","00"};

        String[] splited_value = s.split(":");
        if(splited_value[2].contains("AM")){
            if(splited_value[0].contains("12")){
                return "00" + ":" + splited_value[1] + ":" + splited_value[2].replace("AM","");
            }else{
                return s.replace("AM","");
            }
        }else{
            if(splited_value[0].contains("12")){
                return splited_value[0] + ":" + splited_value[1] + ":" + splited_value[2].replace("PM","");
            }else{
                for(int i = 0 ; i < time_12.length ; i++){
                    if(time_12[i].contains(splited_value[0])){
                        index = i;
                        break;
                    }
                }
                return time_24[index] + ":" + splited_value[1] + ":" + splited_value[2].replace("PM","");
            }
        }
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversionMethod(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
