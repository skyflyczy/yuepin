package com.yp.common.util;

import java.math.BigDecimal;

/**
 * 
 * @author tongzhui.peng
 * 2013-5-8 下午2:26:40
 */

public class CommonUtil {

	/**
	 * 转换成int
	 * @param s
	 * @param i
	 * @return
	 * @author peng.liu
	 */
	public static int parseInt(Object s, int i) {
		try {
			return Integer.parseInt(s.toString());
		} catch (Exception e) {
			return i;
		}
	}
	
	/**
	 * 转化成int
	 * @param s
	 * @return
	 * @author peng.liu
	 */
	public static int parseInt(Object s) {
		return parseInt(s, 0);
	}
	
	/**
	 * 转化成double
	 * @param s
	 * @param i
	 * @return
	 * @author peng.liu
	 */
	public static double parseDouble(Object s, double i) {
		try {
			return Double.parseDouble(s.toString());
		} catch (Exception e) {
			return i;
		}
	}
	
	/**
	 * 转换成double
	 * @param s
	 * @return
	 * @author peng.liu
	 */
	public static double parseDouble(Object s) {
		return parseDouble(s, 0.0);
	}

	/**
	 * 转换成BigDecimal
	 * @param s
	 * @return
	 * @author peng.liu
	 */
	public static BigDecimal parse(Object s) {
		try {
			return new BigDecimal(s.toString());
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	/**
	 * 元=》分
	 * @param applicationamount
	 * @return
	 * @author peng.liu
	 */
    public static String yuan2Cent(String applicationamount) {
        double fltAppAmount = Double.valueOf(applicationamount).doubleValue();
        return yuan2Cent(fltAppAmount);
    }

    /**
     * 元=》分
     * @param applicationamount
     * @return
     * @author peng.liu
     */
    public static String yuan2Cent(double applicationamount) {
        double fltAppAmount = applicationamount;
        long lAppAmount = Math.round(fltAppAmount * 100);
        String applicationamountCent = String.valueOf(lAppAmount);// 交易金额(分)
        return applicationamountCent;
    }
    /**
     * null对象转换为空字符，否则返回其字符内容
     * @param obj
     * @return
     */
    public static String convert(Object obj){
    	if(obj==null) return "";
    	return obj.toString();
    }
}
