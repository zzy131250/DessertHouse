package com.justin.desserthouse.service.impl;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Justin on 2016/2/5.
 */
public class Utils {

    public static String md5(String s) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b & 0xff;
                if (bt < 16) stringBuffer.append(0);
                stringBuffer.append(Integer.toHexString(bt));
            }
            result = stringBuffer.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')  return false;
        }
        return true;
    }

    public static int level(double money) {
        if (money >= 200 && money <= 500) return 1;
        else if (money > 500 && money <= 800) return 2;
        else return 3;
    }

    public static double discount(int level) {
        if (level == 1) return 0.8;
        else if (level == 2) return 0.7;
        else return 0.65;
    }

    public static boolean isTheSameDay(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        if (year1 == year2 && day1 == day2) return true;
        return false;
    }

    public static Date getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getMonthEnd() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    public static double numberFormatter(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(number));
    }

}
