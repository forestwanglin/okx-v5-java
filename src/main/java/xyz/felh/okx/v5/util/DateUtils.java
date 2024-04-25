package xyz.felh.okx.v5.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String FORMAT_Y = "yyyy";
    public static final String FORMAT_D_1 = "yyyy/MM/dd";
    public static final String FORMAT_D_2 = "yyyy-MM-dd";
    public static final String FORMAT_D_3 = "yyyyMMdd";
    public static final String FORMAT_D_4 = "yyyy.MM.dd";
    public static final String FORMAT_D = "dd";
    public static final String FORMAT_DT_1 = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_DT_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DT_3 = "yyyyMMdd HH:mm:ss";
    public static final String FORMAT_DT_4 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DT_5 = "yyyy.MM.dd HH:mm:ss";
    public static final String FORMAT_DT_6 = "yyyyMMddHHmmss";
    public static final String FORMAT_DT_7 = "yyyyMMddHH";
    public static final String FORMAT_M_1 = "yyyy/MM";
    public static final String FORMAT_M_2 = "yyyy-MM";
    public static final String FORMAT_M_3 = "yyyyMM";
    public static final String FORMAT_M = "MM";
    public static final String FORMAT_MD_1 = "MM/dd";
    public static final String FORMAT_MD_2 = "MM-dd";
    public static final String FORMAT_MD_3 = "MMdd";
    public static final String FORMAT_T_1 = "HH:mm:ss";
    public static final String FORMAT_T_2 = "HH:mm";
    public static final String FORMAT_TH = "HH";
    public static final String FORMAT_TM = "mm";
    public static final String FORMAT_TS = "ss";
    public static final String FORMAT_UTC_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String format(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param format   format
     * @param date     date
     * @param timeZone 时区数字 -8, 0, 8 等
     * @return date string
     */
    public static String format(String format, Date date, int timeZone) {
        timeZone = timeZone % 13;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ZoneId zoneId = ZoneId.of("GMT" + (timeZone >= 0 ? "+" : "") + timeZone);
        TimeZone tz = TimeZone.getTimeZone(zoneId);
        sdf.setTimeZone(tz);
        return sdf.format(date);
    }

    public static Date parse(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            return sdf.parse(dateString);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date parse(String dateString, String format, int timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ZoneId zoneId = ZoneId.of("GMT" + (timeZone >= 0 ? "+" : "") + timeZone);
        TimeZone tz = TimeZone.getTimeZone(zoneId);
        sdf.setTimeZone(tz);
        try {
            return sdf.parse(dateString);
        } catch (ParseException var4) {
            return null;
        }
    }

}
