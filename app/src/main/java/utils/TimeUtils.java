package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Viet Hua on 04/10/2020.
 */
public class TimeUtils {
    public static final String VN_DATE_FORMAT = "EEEE dd/MM/YYYY";

    public static String getDateFormat(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        String formattedDate = dateFormat.format(calendar.getTime());
        return formattedDate;
    }

    public static long getCurrentDateInMilliseconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getDateFormatInMilliseconds(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTimeInMillis();
    }

    public static String convertMillisecondsToDateFormat(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(VN_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
