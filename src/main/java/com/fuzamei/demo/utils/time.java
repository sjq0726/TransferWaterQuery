package com.fuzamei.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class time {
	
	public static void main(String[] args) {
		/*// 获取当前系统时间
        System.out.println(DateTime.now());
        // 获取当前格林威治时间
        DateTime d = DateTime.now(DateTimeZone.UTC);
        // 还是当前系统时间
        System.out.println(d.toDate());
        // 当前格林威治时间
        String str = d.toString("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(str);
        // 当前格林威治时间
        Date date = DateUtils.parseDate(str, new String[] { "yyyy-MM-dd HH:mm:ss SSS" });
        System.out.println(date);
        // 当前格林威治时间
        System.out.println(new DateTime(d.getYear(), d.getMonthOfYear(), d.getDayOfMonth(), d.getHourOfDay(), d
                .getMinuteOfHour(), d.getSecondOfMinute()).toDate());
        System.out.println();*/

        // 设置基础时间为格林威治时间
        TimeZone gmtTz = TimeZone.getTimeZone("GMT+08:00");
        Date mydate = new Date();
        
        //getNextDay(mydate);
        //System.out.println(mydate);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setTimeZone(gmtTz);
        System.out.println(df.format(mydate));

       /* SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(df1.parse(df.format(mydate)));
        //System.out.println(df1.format(date));
*/		}
	public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(calendar.MONTH, -3);
       // calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    }
}
