package cn.zs.learn.java.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainApp {
    public static void main(String[] args) throws ParseException {
            // 系统当前时间戳
        System.out.println(System.currentTimeMillis());

        // java.util.Date
        
        // 标示一个精确到毫秒的瞬间，但大部分方法已过时
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toString());
        Date date2 = new Date(System.currentTimeMillis()+1000);
        // 获取时间戳
        long time = date2.getTime();
        System.out.println(time);
        // 比较时间
        System.out.println(date2.before(date));
        System.out.println(date2.after(date));
        // 设置时间
        date2.setTime(System.currentTimeMillis()+10000000);
        System.out.println(date2);

        
            // java.util.CalendarCalendar的默认实现java.util.GregorianCalendar格里高利日历，也就是公历
        
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        Date time2 = calendar.getTime();
        System.out.println(time2);
        System.out.println(time2.getTime());
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        // Date 代表日， MONTH和DAY_OF_WEEK都是从0开始
        System.out.println(calendar2.get(Calendar.DATE));
        System.out.println(calendar2.get(Calendar.ALL_STYLES));
        System.out.println(calendar2.get(Calendar.MONTH));
        System.out.println(calendar2.get(Calendar.DAY_OF_WEEK));
        
        
        // java.text.DataFormat 抽象类,开发中很少用，获得实例的也是java.text.SimpleDateFormat类
        DateFormat dateInstance = DateFormat.getDateInstance();
        DateFormat timeInstance = DateFormat.getTimeInstance();
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
        
        System.out.println(dateInstance);
        System.out.println(timeInstance);
        System.out.println(dateTimeInstance);
        System.out.println(dateInstance.getClass().getName());
        
                /*   
           *  java.text.SimpleDateFormat可以非常灵活的格式化Date, 也可以用于解析各种格式的日期字符串
                    * 创建SimpleDateFormat对象时需要传入一个pattern字符串(日期模板)
                    * 
           *  yyyy和YYYY代表年份 ，MM 代表月份 ，dd 代表当月天数，DD 代表当年第几天 ，
           *  HH代表小时（24时制），hh 代表小时（12时制），mm 代表分钟，ss 代表秒
                 */
         DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             //  date ---> string
         Date date3 = new Date();
         String string = format.format(date3);
         System.out.println(string);
            // String ---> date
         String timeString = "2015-12-30 08:53:21";
         Date parse = format.parse(timeString);
         System.out.println(parse);
    }
}
