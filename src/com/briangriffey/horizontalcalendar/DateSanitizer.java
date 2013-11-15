package com.briangriffey.horizontalcalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by briangriffey on 11/14/13.
 */
public class DateSanitizer {

    public Set<Date> sanitizeDates(Set<Date> dates) {
        if (dates == null)
            return null;

        Set<Date> truncatedDates = new HashSet<Date>();
        Calendar calendar = Calendar.getInstance();

        for (Date date : dates) {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            truncatedDates.add(calendar.getTime());
        }
        return truncatedDates;
    }

    public Date sanitizeDate(Date date) {
        if (date == null)
            return date;


        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
