package com.woniu.cq.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String Time(Date date, String str){
        String format = new SimpleDateFormat(str).format(date);
        return format;
    }

}
