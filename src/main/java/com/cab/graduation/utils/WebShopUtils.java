package com.cab.graduation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WebShopUtils {

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddhhmmss");
	private static int number = 0;

	private static synchronized int getNumber() {
		return ++number;
	}

	private static synchronized void resetNumber() {
		number = 0;
	}

	/**
	 * 可用来检查String数组中是否有null的元素,若有返回false,否则返回true
	 * @param strs
	 * @return
	 */
	public static boolean hasEmpty(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (str == null || str.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 如果集合不为空,返回该集合的第一个元素,否则返回null
	 * @param list
	 * @return
	 */
	public static <T> T getFirst(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 用来生成订单序列号
	 * @return
	 */
	public static String generateOrderSerialNum() {

		String dateString = dateFormat.format(new Date());
		String number = getNumber() + "";
		//一轮是一亿个订单
		if (number.length() > 9) {
			resetNumber();
		}
		//刚开始i为1最大为8,为8后加一个"0"那么长度就为9了
		for (int i = number.length(); i <= 8; i++) {
			number = "0" + number;
		}
		return "A" + dateString + number;
	}
	
	/**
	 * 生成激活序列号
	 * @return
	 */
	public static String generateActiveSerialNum(){
		//初始化不同的字符
		char[] letters = {'A', 'C', 'B', 'D', 'F','E', 'H', 'G', 'Y', 'Z'};
	       
		String tStr = String.valueOf(System.currentTimeMillis());
		
		//因为tStr的字符只有‘0’-‘9’，我们可以把它看成索引到letters中找相应的字符，这样相当于“置换”，所以是不会重复的.
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tStr.length();i++)
		{
		  sb.append(letters[tStr.charAt(i)-'0']);
		}
		return UUID.randomUUID().toString()+"-"+sb.toString();
	}
	
	
	//关闭资源的方法
	public static void closeQuietly(AutoCloseable ac){
		if(ac!=null){
			try {
				ac.close();
			} catch (Exception e) {
				//do nothing
			}
		}
	}
}
