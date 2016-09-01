package com.github.ikivil.commons.dates;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class KayDateUtil {

	public static final String yyyy = "yyyy";
	
	public static final String yyyyMM = "yyyyMM";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyymmddHH = "yyyyMMddHH";
	public static final String yyyymmddHHmm = "yyyyMMddHHmm";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";  //2014 06 01 08 30 29
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	
	public static final String yyyy_MM = "yyyy-MM";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	
	
	public static final String yyyy年MM月 = "yyyy年mm月";
	public static final String yyyy年MM月dd日 = "yyyy年MM月dd日";
	public static final String yyyy年MM月dd日HH时 = "yyyy年MM月dd日HH时";
	public static final String yyyy年MM月dd日HH时mm分 = "yyyy年MM月dd日HH时mm分";
	public static final String yyyy年MM月dd日HH时mm分ss秒 = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String yyyy年MM月dd日HH时mm分ss秒SSS毫秒 = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
	
	//special 2014 06 01 8 30 29 
	public static final String yyyyMMddHmmss = "yyyyMMddHmmss";
	public static final String yyyy_MM_dd_H_mm_ss = "yyyy-MM-dd H:mm:ss";
	public static final String yyyy年MM月dd日H时mm分ss秒 = "yyyy年MM月dd日H时mm分ss秒";
	
	
	public static final String[] formatArr = {yyyy,yyyyMM,yyyyMMdd,yyyymmddHH,yyyymmddHHmm,yyyyMMddHHmmss,yyyyMMddHHmmssSSS,yyyyMMddHmmss};
	
	/**
	 * Date To String
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date,String format){
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * Date To Long
	 * @param date
	 * @return
	 */
	public static Long dateToLong(Date date){
		return date.getTime();
	}
	/**
	 * String To Date
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		//参数 格式可以包含数字汉字等，数字顺序组合必须符合日期格式如：formatArr内容格式。
		String format="";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if((c >= 0x4e00)&&(c <= 0x9fbb)) {//Get Chinese  
			}
			if(c>='0' && c<='9'){// Get Number
				sb.append(c);
			}
		}
		System.out.println("参数："+str+"  数字："+sb.toString());
		for(int i=0;i<formatArr.length;i++){
			if(formatArr[i].length()==sb.length()){
				format =formatArr[i];
			}
		}
		if(format.equals("")){
			System.out.println("此str 格式暂不支持转换为 Date！");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * String To Long
	 * @param dateStr
	 * @return
	 */
	public static Long stringToLong(String dateStr){
		Date date = stringToDate(dateStr);
		return dateToLong(date);
	}
	
	/**
	 * Long To Date
	 * @param dateLong
	 * @return
	 */
	public static Date longToDate(Long dateLong){
		return new Date(dateLong);
	}
	/**
	 * Long To String
	 * @param dateLong
	 * @param format
	 * @return
	 */
	public static String longToString(Long dateLong,String format){
		Date date = new Date(dateLong);
		return dateToString(date,format);
	}
	
	
	/**
	 * 日期加减 by year
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAddYear(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR,n);
		return c.getTime();
	}
	
	/**
	 * 日期加减 by month
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAddMonth(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,n);
		return c.getTime();
	}
	/**
	 * 日期加减 by day
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAddDay(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR,n);
		return c.getTime();
	}
	
	/**
	 * 月份差
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
	
	
	/**
	 * BigDecimal 保留N位小数
	 * @param decimal
	 * @param n
	 * @return
	 */
	public static String bigDecimalToPointNString(BigDecimal decimal,int n){
		return String.format("%."+n+"f", decimal);
	}
	
	
	/**
	 * BigDecimal 保留N位小数
	 * @param decimal
	 * @param n
	 * @return
	 */
	public static BigDecimal bigdecimalToPointN(BigDecimal decimal,int n){
		return new BigDecimal(String.format("%."+n+"f", decimal));
	}
	
	
	/**
	 * 返回 min 到 max 的随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min,int max){
		  return  new Random().nextInt((max - min) + 1) + min;
	}
	
	
	
	
	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
