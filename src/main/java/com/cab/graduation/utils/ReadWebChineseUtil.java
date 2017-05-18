package com.cab.graduation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadWebChineseUtil {
	
	public static String baseUrl;
	public static String contentType;
	public static String uid;
	public static String key;
	public static String smsMob;
	public static String smsText;
	
	static{
		Properties properties=new Properties();
		
		String path=ReadWebChineseUtil.class.getResource("/webchinese.properties").getPath();
		System.out.println(path);
		InputStream in=null;
		try{
			in=new FileInputStream(path);
			properties.load(in);
			baseUrl = properties.getProperty("baseUrl");
			contentType = properties.getProperty("contentType");
			uid = properties.getProperty("Uid");
			key = properties.getProperty("Key");
		}catch(IOException e){
			throw new RuntimeException("webchinese.properties文件解析异常:"+e);
		}finally{
			WebShopUtils.closeQuietly(in);
		}
	}
	
}
