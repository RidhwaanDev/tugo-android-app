package com.ridhwaan.any.tugo;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ridhwaan on 2/4/17.
 */

public class Utils {

    public static int day;
    public static int month;
    public static int year;



    public static Date getCurrentDate(){

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        return date;
    }

    public static int[] getFormattedDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        return new int[] {day,month,year};


    }



}
