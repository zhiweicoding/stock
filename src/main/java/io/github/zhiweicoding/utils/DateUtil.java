package io.github.zhiweicoding.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Created by zhiwei on 2022/3/13.
 */
public class DateUtil {

    private static final SimpleDateFormat SDF_DATE_TIME_ALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter LOCAL_DATE_TIME_ALL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime getLdt(String val) {
        return LocalDateTime.parse(val, LOCAL_DATE_TIME_ALL);
    }

    public static long getTimeStamp(String val) {
        LocalDateTime parse = LocalDateTime.parse(val, LOCAL_DATE_TIME_ALL);
        return parse.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
