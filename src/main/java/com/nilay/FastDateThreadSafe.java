package com.nilay;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

public class FastDateThreadSafe {

    public static void main(String[] args) throws ParseException{
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        FastDateFormat fdf=FastDateFormat.getInstance(dateFormat);//("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date lastModifiedDate = fdf.parse(fdf.format(date));
        System.out.println("From convertStringToDate ");
        System.out.println(lastModifiedDate);
    }
}
