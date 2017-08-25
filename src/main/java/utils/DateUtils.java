package utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by plash on 22/02/17.
 */
public class DateUtils {

    public static final String ASIA_KOLKATA_ZONE_STRING = "Asia/Kolkata";

    public static DateTime getISTDateTimeFromStringInFormat(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        return formatter.parseDateTime(date).withZoneRetainFields(DateTimeZone.forID(ASIA_KOLKATA_ZONE_STRING));
    }

    public static Long getTimestampAfterEpoch(String date, String format) {
        return DateUtils.getISTDateTimeFromStringInFormat(date, format).getMillis() / 1000;
    }

}
