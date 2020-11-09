package com.woniu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UUIDUtil {
    public static  Integer getOrderNo(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());

        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf.substring(8,14) ;
        return (int)Long.parseLong(orderNo);
    }
}
