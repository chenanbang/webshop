package com.cab.graduation.mail.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cab.graduation.utils.ReceiveSourcePathUtils;
import com.cab.graduation.utils.WebShopUtils;

public class SimpleMailDemo {

	private static final Properties properties=new Properties();
	
	static {
		InputStream inStream=null;
		try {
			inStream=new FileInputStream(ReceiveSourcePathUtils.sourcePathMap.get("sendMailConfig"));
			properties.load(inStream);
		} catch (IOException e) {
			throw new RuntimeException("IO异常:"+e);
		}finally {
			WebShopUtils.closeQuietly(inStream);
		}
	}
	
	public static void main(String[] args) throws Exception {
		//sendMail();
		
		String title="张三,今天下午到我办公室一趟";
		String content="<a href='http://localhost:8080/webshop/showRegisterPage'>需要提前看的内容</a>";
		
		String toEmail="zhangsan@webshop.com";
		sendMail2(title, content, toEmail);
	}
	
	public static void sendMail() throws MessagingException{
		//1、创建Properties对象
		Properties props = new Properties();
		//props = System.getProperties();
		//设置传输协议
		props.put("mail.transport.protocol", "smtp");
		//设置发信邮箱的smtp地址
		props.put("mail.smtp.host", "localhost");
		//是否进行身份验证
		props.put("mail.smtp.auth", "true");
		//是否进行调试
		//props.put("mail.debug", "true");

		//2、创建session对象
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("admin@webshop.com", "admin");
					}
				});

		//3、编写邮件Message
		Address from = new InternetAddress("admin@webshop.com");
		Address to = new InternetAddress("zhangsan@webshop.com");
		
		//Message存储发送的电子邮件信息
		Message message = new MimeMessage(session);
		message.setFrom(from);
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject("邮件标题-简单邮件示例");
		String content="Hi @chenanbang!<br/><br/>"
				+ "<a href='https://github.com/users/chenanbang/emails/27503516/confirm_verification/ce5dfe6d34f507704f27e703f8b4adc7ddf08e4b?utm_campaign=github-email-verification&utm_content=html&utm_medium=email&utm_source=verification-email'>Verify email address</a>"
				+ "<br/><hr/><br/>"
				+ "Button not working? Paste the following link into your browser:<br/>"
				+ "<a href='https://github.com/users/chenanbang/emails/27503516/confirm_verification/ce5dfe6d34f507704f27e703f8b4adc7ddf08e4b'>https://github.com/users/chenanbang/emails/27503516/confirm_verification/ce5dfe6d34f507704f27e703f8b4adc7ddf08e4b</a><br/><br/>"
				+ "You’re receiving this email because you recently created a new GitHub account or added a new email address. If this wasn’t you, please ignore this email.";
		message.setContent(content, "text/html;charset=utf-8");
//				message.setContent("这是简单的文本邮件...", "text/html;charset=utf-8");

		//4、创建transport邮件发送对象
		//Transport.send(message);

		Transport transport = session.getTransport();
		transport.connect();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	public static void sendMail2(String title, String content,String toEmail) throws Exception {
//        Properties properties = new Properties();// 创建Properties对象
//        properties.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
//        properties.put("mail.smtp.host", "smtp.163.com");// 设置发信邮箱的smtp地址
//        properties.setProperty("mail.smtp.auth", "true"); // 验证
//        properties.put("mail.debug", "true");//便于调试
        
        //使用验证，创建一个Authenticator
        Authenticator authenticator = new MailAuthenticator(properties.getProperty("emailName"), properties.getProperty("emailPassword")); 
        		
        //根据Properties，Authenticator创建Session
        Session session = Session.getDefaultInstance(properties, authenticator);
        //Message存储发送的电子邮件信息
        Message message = new MimeMessage(session);
        
        //设置发送人和接收人的email地址
        message.setFrom(new InternetAddress(properties.getProperty("fromEmail")));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));// 设置收信邮箱
        
        //指定邮箱内容及ContentType和编码方式
        message.setContent(content, "text/html;charset=utf-8");
        //设置邮件主题
        message.setSubject(title);
        //设置发信时间
        message.setSentDate(new Date());
        //执行发送操作
        Transport.send(message);
    }
}
