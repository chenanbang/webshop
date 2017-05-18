package com.cab.graduation.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmailUtil {

	public static void main(String[] args) throws EmailException, MalformedURLException {

		String title="你好";
		String content="<a href='http://www.baidu.com'>百度</a>";
		
		String toEmail="peachyx@126.com";
		
		sendActiveEmail(title, content, "lisi@webshop.com");
		
		sendActiveEmail(title, content, toEmail);
	}
	
	public static void sendActiveEmail(String title,String content,String toEmail) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("mail.webshop.com");
		try {
			email.addTo(toEmail);
			email.setFrom("admin@webshop.com", "管理员");
			email.setSubject(title);
			email.setContent(content, "text/html;charset=utf-8");			
			email.send();
		} catch (EmailException e) {
			throw new RuntimeException("邮件发送异常:"+e);
		}
	}
	
	public static void sendHtmlEmail() throws EmailException, MalformedURLException{
		HtmlEmail email = new HtmlEmail();
		email.setHostName("mail.webshop.com");
		email.addTo("zhangsan@webshop.com");
		email.setFrom("admin@webshop.com", "管理员");
		email.setSubject("Test email with inline image");
		  
		// embed the image and get the content id
		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//		URI uri=new File("f:/666.jpg").toURI();
//		URL url=uri.toURL();
		
		String cid = email.embed(url, "Apache logo");
		  
		// set the html message
		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		// send the email
		email.send();
	}
}
