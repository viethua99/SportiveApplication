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

}
