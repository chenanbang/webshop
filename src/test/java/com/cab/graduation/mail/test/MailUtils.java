package com.cab.graduation.mail.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.cab.graduation.utils.ReceiveSourcePathUtils;
import com.cab.graduation.utils.WebShopUtils;

import sun.misc.BASE64Encoder;

public class MailUtils {

	private static Session session;
	public static final Properties props = new Properties();
	static {
		// 1、properties
		InputStream inStream=null;
		try {
			inStream=new FileInputStream(ReceiveSourcePathUtils.sourcePathMap.get("sendMailConfig"));
			props.load(inStream);
		} catch (IOException e) {
			throw new RuntimeException("IO异常:"+e);
		}finally {
			WebShopUtils.closeQuietly(inStream);
		}
		// 2、创建session对象
		session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("emailName"), props.getProperty("emailPassword"));
			}
		});
	}

	public static String base64Encoding(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}

	public static Message createMessage() {
		return new MimeMessage(session);
	}

	public static void sendMessage(Message message) throws MessagingException {
		Transport.send(message);
	}
	public static void main(String[] args) {
		
	}
}
