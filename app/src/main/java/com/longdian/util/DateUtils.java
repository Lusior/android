package com.longdian.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期帮助类,提供常用的日期类方法
 *
 * @author: dk
 */
public class DateUtils {

    private static SimpleDateFormat df = new SimpleDateFormat();
    private static List<String> patterns = new ArrayList<String>();

    static {
        patterns.add("yyyy-MM-dd HH:mm:ss");
        patterns.add("yyyy-MM-dd HH:mm");
        patterns.add("yyyy-MM-dd");
        patterns.add("yyyy-MM");
        patterns.add("yyyy/MM/dd HH:mm:ss");
        patterns.add("yyyy/MM/dd HH:mm");
        patterns.add("yyyy/MM/dd");
        patterns.add("yyyy/MM");
        patterns.add("yyyyMMdd HH:mm:ss");
        patterns.add("yyyyMMdd HH:mm");
        patterns.add("yyyyMMddHHmmss");
        patterns.add("yyyyMMddHHmm");
        patterns.add("yyyyMMdd");
        patterns.add("yyyyMM");
        patterns.add("yyyy");
    }

    /**
     * 下面两行代码要放在静态代码块下面 否则拿不到patterns
     */
    public static Date MIN_DATE = toDate("1971");
    public static Date MAX_DATE = toDate("3000");

    /**
     * 从String 类型转换成 Date类型
     */
    public static Date toDate(String time) {
        Iterator<String> it = patterns.iterator();
        while (it.hasNext()) {
            try {
                df.applyPattern(it.next());
                return df.parse(time);
            } catch (ParseException pattern) {
            }
        }
        return null;
    }

    /**
     * 从Date 类型 根据模式 转换成 String 类型
     */
    public static String toString(Date time, String format) {
        df.applyPattern(format);
        return null == time ? "" : df.format(time);
    }

    /**
     * 设定模式 返回当前日期字符串,部分模式如下: yyyy-MM-dd yyyy/MM/dd HH:mm:ss
     */
    public static String getDateTime(String format) {
        return toString(new Date(), format);
    }

    /**
     * 设定模式 返回当前日期上个月字符串,部分模式如下: yyyy-MM-dd yyyy/MM/dd HH:mm:ss
     */
    public static String getLastMonth(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date m = calendar.getTime();
        return toString(m, format);
    }

    /**
     * 日期格式转换 yyyyMMdd 转成  yyyy-MM-dd
     *
     * @param dt
     * @return
     * @author lgz
     * @date 2015-06-04
     */
    public static String transferDt(String dt) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String retStr = "";
        try {
            retStr = sf2.format(sf1.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public static String transferDt(String dt, String toStringFormat) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat(toStringFormat);
        String retStr = "";
        try {
            retStr = sf2.format(sf1.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 日期格式转换 yyyy-MM-dd 转成  yyyyMMdd
     */
    public static String transferDt3(String dt) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String retStr = "";
        try {
            retStr = sf1.format(sf2.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 日期格式转换 yyyyMMdd 转成  yyyy-MM
     *
     * @param dt
     * @return
     * @author lgz
     * @date 2015-06-04
     */
    public static String transferDt2(String dt) {
        return dt.substring(0, 4) + "-" + dt.substring(4, 6);
    }

    /**
     * 获取日期格式为 yyyyMMdd日期
     *
     * @return
     * @author lgz
     * @date 2015-07-22
     */
    public static String getYmdDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String retStr = sdf.format(new Date());
        return retStr;
    }

    /**
     * 获取日期格式为 yyyy-MM-dd 日期
     */
    public static String getYmdDate2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String retStr = sdf.format(new Date());
        return retStr;
//        return "2017-06-17";
    }

    /**
     * 获取前一天日期，格式为 yyyy-MM-dd 日期
     */
    public static String getYesterdayDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        return yesterday;
//        return "2017-06-17";
    }

    /**
     * 获取前一天日期，格式为 yyyy-MM-dd 日期
     */
    public static String getDaysAgo(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        return yesterday;
//        return "2017-06-17";
    }


    /**
     * 时间格式转换 HHmmss 转成  HH:mm:ss
     *
     * @param tm
     * @return
     * @author lgz
     * @date 2015-06-04
     */
    public static String transferTm(String tm) {
        SimpleDateFormat sf1 = new SimpleDateFormat("HHmmss");
        SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
        String retStr = "";
        try {
            retStr = sf2.format(sf1.parse(tm));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 获取时间格式为HHmmss时间
     *
     * @return
     * @author lgz
     * @date 2015-07-22
     */
    public static String getHmsTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String retStr = sdf.format(new Date());
        return retStr;
    }

    /**
     * 日期格式转换 yyyyMMddHHmmss 转成  yyyy-MM-dd HH:mm:ss
     *
     * @param dt
     * @return
     * @author lgz
     * @date 2015-06-11
     */
    public static String transferDtTm(String dt) {
        if (dt == null || "".equals(dt)) {
            return "";
        }
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStr = "";
        try {
            retStr = sf2.format(sf1.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 获取日期格式为 yyyyMMddHHmmss的时间
     *
     * @return
     * @author lgz
     * @date 2015-07-22
     */
    public static String getYmdHmsDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String retStr = sdf.format(new Date());
        return retStr;
    }

    /**
     * 获取日期格式为 yyyy-MM-dd HH:mm:ss的时间
     *
     * @return
     * @author lgz
     * @date 2015-07-22
     */
    public static String getYmdHmsDate2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStr = sdf.format(new Date());
        return retStr;
    }

    /**
     * 获取日期格式为yyyyMMdd的日期n天以后的日期
     *
     * @return yyyyMMdd
     * @author zxl
     * @Time 2015年9月10日下午8:37:26
     */
    public static String getnDayAfter(String yyyyMMdd, int n) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = toDate(yyyyMMdd);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        String res = formatter.format(cal.getTime());
        return res;
    }

    /**
     * 获取日期格式为yyyyMMdd的日期n月以后的日期
     *
     * @return yyyyMMdd
     * @author zxl
     * @Time 2015年9月10日下午8:37:26
     */
    public static String getnMonthAfter(String yyyyMMdd, int n) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = toDate(yyyyMMdd);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        String res = formatter.format(cal.getTime());
        return res;
    }

    public static String transferTm() {
        return null;
    }

    /**
     * 获取上一日的日期
     *
     * @author lgz
     * @date 2017年1月4日
     * @version 1.0
     */
    public static String getLastDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        String res = formatter.format(cal.getTime());
        return res;
    }

    public static void geta() {

/*	    Locale locale = new Locale("en", "US");  
        System.out.println(locale.getDisplayCountry(Locale.US));
	    
	    //Locale locale2 = Locale.CHINA; 
	    //Calendar cal =  Calendar.getInstance(locale);
	    //TimeZone timeZone = cal.getTimeZone();
	    //System.out.println(timeZone.getDisplayName(locale));
	    Date date = new Date();    
	    TimeZone timeZoneMiami = TimeZone.getTimeZone("");
	    TimeZone timeZoneMiami2 = TimeZone.getTimeZone("United States");
	    DateFormat df2 = DateFormat.getDateTimeInstance( DateFormat.SHORT,DateFormat.SHORT, Locale.US);
	    df2.setTimeZone(timeZoneMiami2);
	    System.out.println(df2.format(date));
	   
	    DateFormat df = DateFormat.getDateTimeInstance( DateFormat.SHORT,DateFormat.SHORT, Locale.CHINA);
	    df2.setTimeZone(timeZoneMiami);
	    System.out.println(df.format(date));*/
        Date date = new Date();
        Locale[] ls = DateFormat.getAvailableLocales();
        for (int i = 0; i < ls.length; i++) {
            if (ls[i] != null && ls[i].getDisplayCountry().trim() != "") {
                System.out.print(ls[i].getDisplayCountry());
                DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, ls[i]);
                System.out.println(ls[i].getDisplayLanguage(Locale.US));
                Calendar cal = Calendar.getInstance(ls[i]);
                //TimeZone timeZone = cal.getTimeZone();
                //TimeZone timeZoneMiami = TimeZone.getTimeZone(ls[i]);
                // System.out.println(ls[i].getDisplayLanguage(Locale.US));
                TimeZone timeZoneMiami = TimeZone.getTimeZone(ls[i].getDisplayLanguage(Locale.US));
                df2.setTimeZone(timeZoneMiami);
                System.out.println(":" + df2.format(date));
            }
        }
        /*DateFormat.getDateTimeInstance(dateStyle, timeStyle, aLocale)
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
	    DateFormat df1 = DateFormat.getDateInstance(DateFormat.HOUR0_FIELD, locale);
	    DateFormat df2 = DateFormat.getDateInstance(DateFormat.HOUR1_FIELD, locale);
	    System.out.println(df.format(date));  
	    System.out.println(df1.format(date));  
	    System.out.println(df2.format(date));  */
    }

    public static void getb() {
        /*TimeZone timeZoneFL = TimeZone.getDefault();
        System.out.println("/n" + timeZoneFL.getDisplayName());
		System.out.println("RawOffset: " + timeZoneFL.getRawOffset());
		System.out.println("Uses daylight saving: " + timeZoneFL.useDaylightTime());
		
		TimeZone timeZoneLondon = TimeZone.getTimeZone("Europe/London");
		System.out.println("/n" + timeZoneLondon.getDisplayName());
		System.out.println("RawOffset: " + timeZoneLondon.getRawOffset());
		System.out.println("Uses daylight saving: " + timeZoneLondon.useDaylightTime());*/
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids)
            System.out.println(id + ", ");


    }

    public static String getDayWeek() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String res = formatter.format(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return res + "  " + weekDays[w];
    }

    /**
     * 在当前日期上增加天数
     *
     * @param num
     * @return
     */
    public static String dayAdd(int num, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, num);
        return sf.format(c.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getYmdDate2());
    }

}