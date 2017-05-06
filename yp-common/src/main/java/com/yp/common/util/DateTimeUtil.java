/**
 * 
 */
package com.yp.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yp.common.consts.Constants;

/**
 * @author chubchen
 *
 */
public class DateTimeUtil {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	
	private static final SimpleDateFormat alipayDateFormat = new SimpleDateFormat(Constants.ALIPAY_DATE_FORMAT);

    private static final SimpleDateFormat icbcDateFormat = new SimpleDateFormat(Constants.ICBC_DATE_FORMAT);

    /**
     * 根据自定义的 格式获得 simpleDateFormat 对象
     */
    public static SimpleDateFormat getSimpDateFormat(String format){
        return new SimpleDateFormat(format);
    }

	/**
	 * 获得n年后的时间
	 * @param date
	 * @return
	 */
	public static Date getYear(Date date, int n)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + n);//让日期加1  
		return calendar.getTime();
	}
    /**
     * 获得今天的开始时间点
     * @param
     * @return
     */
    public static Date getToday()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    
    /**
     * 获得上一天的日期
     * @return
     */
    public static Date getLastDate()
    {
    	return new Date(System.currentTimeMillis() - 24*60*60*1000);
    }
    
    /**
     * 获得milliseconds毫秒之后的时间
     * @param
     * @return
     */
    public static Date getDateAfterMilliseconds(long milliseconds)
    {
       return new Date(System.currentTimeMillis() + milliseconds);
    }

    /**
     * 获得milliseconds毫秒之前的时间
     * @param milliseconds
     * @return
     */
    public static Date getDateBeforeMilliseconds(long milliseconds)
    {
        return new Date(System.currentTimeMillis() - milliseconds);
    }
    
    /**
     * 获得milliseconds毫秒之后的时间
     * @param
     * @return
     */
    public static Date getDateAfterMilliseconds(Date date, long milliseconds)
    {
       return new Date(date.getTime() + milliseconds);
    }


    /**
     * 获得milliseconds毫秒之后的时间
     * @param
     * @return
     */
    public static Date getDateAfterMilliseconds(Date date, String milliseconds)
    {
        return new Date(date.getTime() + Long.parseLong(milliseconds));
    }

    /**
     * 获得milliseconds毫秒之后的时间
     * @param
     * @return
     */
    public static String getCurrAfterMillisecondsFormat(String milliseconds,String format)
    {
        SimpleDateFormat format1 =  getSimpDateFormat(format);
        return   format1.format(new Date(new Date().getTime() + Long.parseLong(milliseconds)));
    }
    /**
     * 获得milliseconds毫秒之前的时间
     * @param
     * @return
     */
    public static Date getDateBeforeMilliseconds(Date date, long milliseconds)
    {
        return new Date(date.getTime() - milliseconds);
    }
    
	/**
	 * 获得当前时间
	 * @return
	 */
	public static String getCurrentTime()
	{
		return dateFormat.format(new Date());
	}

    /**
     * 获得当前时间
     * @return
     */
    public static Date getCurrentDate()
    {
        return new Date();
    }
	
	/**
	 * 获得对应时间的标准字符串格式
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date)
	{
		return dateFormat.format(date);
	}
	
	/**
	 * 获得支付宝通知时间
	 * @param date
	 * @return
	 */
	public static String alipayGetDateString(Date date)
	{
		return alipayDateFormat.format(date);
	}
	
	public static Date getDateFromString(String dateString) throws ParseException
	{
		return dateFormat.parse(dateString);
	}
	
	public static Date alipayGetDateFromString(String dateString) throws ParseException
	{
		return alipayDateFormat.parse(dateString);
	}

    public static Timestamp getTimestampFromString(String dateString) throws ParseException
    {
        return new Timestamp(alipayDateFormat.parse(dateString).getTime());
    }

    public static String getFromStrTimestamp(Timestamp timestamp,String dateString) throws ParseException
    {
        Date date = new Date(timestamp.getTime());
        return getDateSimpleFormat(date,dateString);
    }

    public static String getFromStrLong(long timestamp,String dateString) throws ParseException
    {
        Date date = new Date(timestamp);
        return getDateSimpleFormat(date,dateString);
    }

    public static void main(String[] args) {

        System.out.println(getToday());
    }

    /**
     * 获得Long 类型的时间转换结果
     * @param date
     * @return
     */
    public static Long getLongNumFromDate(Date date){
        return   Long.parseLong(getSimpDateFormat(Constants.LONG_FORMAT).format(date));
    }
    /**
     * 获得Int 类型的时间转换结果
     * @param date
     * @return
     */
    public static int getShortNumFromDate(Date date){
        return   Integer.parseInt(getSimpDateFormat(Constants.SHORT_FORMAT).format(date));
    }

    /**
     * 获得当前时间 将当前时间转换为自己需要的格式
     * 如 ： yyyyMMddHH 获得 2014060410
     * @param dataFormat
     * @return
     */
    public static String getCurrentByFormat(String dataFormat){
        SimpleDateFormat format = getSimpDateFormat(dataFormat);
        return  format.format(new Date());
    }

    /**
     * 获得工行商城的时间格式
     * @return
     */
    public static String getIcbcDateStr(String dateStr) throws Exception
    {
        Date date = dateFormat.parse(dateStr);
        return icbcDateFormat.format(date);
    }

    /**
     *  将传入的时间转换
     */

    public static String getDateSimpleFormat(Date date ,String format){
        SimpleDateFormat format1 = getSimpDateFormat(format);
        return  format1.format(date);
    }

    /**
     * 将提供的字符串 转化为制定格式的Data时间
     */

    public static Date getDateSimpleFormatByString(String date,String format)throws Exception{
        SimpleDateFormat format1 = getSimpDateFormat(format);
        return format1.parse(date);
    }
}
