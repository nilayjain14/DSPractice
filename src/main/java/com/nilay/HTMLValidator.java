package com.nilay;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import static java.util.regex.Pattern.CASE_SENSITIVE;


public class HTMLValidator {

    public static void main(String[] args) {
        String regex = "\\<(.+)\\>([^\\<\\>]+)\\<\\/\\1\\>";
        Pattern p = Pattern.compile(regex);
        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());
        while (numSentences-- > 0) {
            int count = 0;
            String input = in.nextLine();

            Matcher m = p.matcher(input);

            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                System.out.println(m.group(2));
                count++;
            }
            if(count==0){System.out.println("None");}
        }

        in.close();
    }
}
