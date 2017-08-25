package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DEFAULT_TIME_ZONE = "IST";

    public static String getISTTimeFromEpoch(Long epoch) {

        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch*1000), ZoneId.of(ZoneOffset.of("+05:30").getId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return date.format(formatter);
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
