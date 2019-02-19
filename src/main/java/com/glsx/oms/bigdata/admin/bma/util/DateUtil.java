package com.glsx.oms.bigdata.admin.bma.util;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends DateUtils {

    /**
     * 日期格式，年份和月份，例如：200707，2008-08
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
     * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化Date时间
     * @param date Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date date, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(date);
    }

    /**
     * 格式化Timestamp时间
     * @param timestamp Timestamp类型时间
     * @param timeFromat
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    public static Date parseStrToDate(String date, String timeFromat) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.parse(date);
    }

    public static void main(String[] args) {
//        BigDecimal b = new BigDecimal((double) 116418261/1000000);
//        System.out.println(b.setScale(6,BigDecimal.ROUND_DOWN));

        System.out.println(parseDateToStr(DateUtil.addDays(new Date(), -30),"yyyy-MM-dd HH:mm:ss"));
    }

}
