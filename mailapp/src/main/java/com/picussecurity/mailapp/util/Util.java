package com.picussecurity.mailapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String convertToFormattedDate(long millis) {
        Date date = new Date(millis);

        // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public static long convertToMillis(String dateParam) {
        long result = -1L;
        try {
            Date date = formatter.parse(dateParam);
            result = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
