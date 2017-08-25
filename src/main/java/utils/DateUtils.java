package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
    public static final String DEFAULT_TIME_ZONE = "IST";

    public static String getISTTimeFromEpoch(Long epoch) {
        Date date = new Date(epoch);
        DateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        return format.format(date);
    }

    public static Long getEpochFromISTDate(String date) {
        return getEpochFromDateInFormat(date, DEFAULT_DATE_FORMAT, DEFAULT_TIME_ZONE);
    }

    public static Long getEpochFromDateInFormat(String date, String format, String timeZone) {

        SimpleDateFormat in = new SimpleDateFormat(format);
        in.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date outDate = null;
        try {
            outDate = in.parse(date);
            return outDate.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Unable to format date");
        }
    }

    public static Boolean isDateInFormat(String date) {
        Date d = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            d = sdf.parse(date);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return d != null;
    }

}
