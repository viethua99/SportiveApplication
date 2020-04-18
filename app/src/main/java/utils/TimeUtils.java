package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Viet Hua on 04/10/2020.
 */
public class TimeUtils {
    public static final String VN_DATE_FORMAT = "EEEE dd/MM/YYYY";
    private static Calendar calendar = Calendar.getInstance();

    public static String getDateFormat(int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        String formattedDate = dateFormat.format(calendar.getTime());
        return formattedDate;
    }

    public static long getCurrentDateInMilliseconds() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String getCurrentDate() {
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        String formattedDate = simpleDateFormat.format(date);
        return formattedDate;
    }

    public static long getDateFormatInMilliseconds(int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        return calendar.getTimeInMillis();
    }

    public static long getDateFormatInMilliseconds(int year, int month, int dayOfMonth, int hour) {
        calendar.set(year, month, dayOfMonth, hour, 0);
        return calendar.getTimeInMillis();
    }

    public static int getCurrentYear() {
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static String convertMillisecondsToDateFormat(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(VN_DATE_FORMAT);
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
