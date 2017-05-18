package com.cab.graduation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String simpleTime=simpleDateFormat.format(new Date());
		Date date1=simpleDateFormat.parse("2017-03-21-18:56:05");
		Date date2=simpleDateFormat.parse("2017-03-22-18:56:05");
		System.out.println((date2.getTime()-date1.getTime())/(1000*60*60.0));
		
	}
}
