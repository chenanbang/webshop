package com.cab.graduation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsgUtil {

	private static final String BASE_URL=ReadWebChineseUtil.baseUrl;
	private static final String CONTENT_TYPE=ReadWebChineseUtil.contentType;
	private static final String UID=ReadWebChineseUtil.uid;
	private static final String KEY=ReadWebChineseUtil.key;
	private static final String SMS_MOB=ReadWebChineseUtil.smsMob;
	private static final String SMS_TEXT=ReadWebChineseUtil.smsText;
	
	public static void sendMsg() throws IOException {
//		HttpClient client = new HttpClient();
//		PostMethod post = new PostMethod(BASE_URL);
//		post.addRequestHeader("Content-Type",CONTENT_TYPE);//在头文件中设置转码
//		NameValuePair[] data ={ new NameValuePair("Uid", UID),new NameValuePair("Key", KEY),new NameValuePair("smsMob",SMS_MOB),new NameValuePair("smsText","验证码为:"+SMS_TEXT)};
//		post.setRequestBody(data);
//		
//		client.executeMethod(post);
//		Header[] headers = post.getResponseHeaders();
//		int statusCode = post.getStatusCode();
//		System.out.println("statusCode:"+statusCode);
//		for(Header h : headers)
//		{
//			System.out.println(h.toString());
//		}
//		String result = new String(post.getResponseBodyAsString().getBytes("utf-8")); 
//		System.out.println(result); //打印返回消息状态
//
//		post.releaseConnection();
	}
	
}
