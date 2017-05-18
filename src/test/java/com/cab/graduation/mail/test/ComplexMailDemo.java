package com.cab.graduation.mail.test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.cab.graduation.utils.ReceiveSourcePathUtils;

public class ComplexMailDemo {

	public static void main(String[] args) throws Exception {
//		 html_mail();
//		 attachment_mail();
		 attachment_html_mail();
	}

	public static void attachment_html_mail() throws Exception {
		Message message = MailUtils.createMessage();
		// ///////////////
		Address from = new InternetAddress(MailUtils.props.getProperty("fromEmail"));
		Address to = new InternetAddress("lisi@webshop.com");

		message.setFrom(from);
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject("html格式及附件结合的邮件");

		// 邮件体
		// html文本
		MimeBodyPart content = new MimeBodyPart();
		content.setContent("html的内容<br/><img src='cid:inner_pic'/>",
				"text/html;charset=utf-8");

		// 图片消息
		MimeBodyPart pic = new MimeBodyPart();
		FileDataSource dataSource = new FileDataSource(ReceiveSourcePathUtils.sourcePathMap.get("sn"));
		DataHandler dataHandler = new DataHandler(dataSource);
		pic.setDataHandler(dataHandler);
		pic.setContentID("inner_pic");

		// 第一次合并，用来内嵌图片
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(content);
		multipart.addBodyPart(pic);
		//说明这是以内嵌的方式
		multipart.setSubType("related");

		// 把复合消息包装成一个bodypart
		MimeBodyPart tempBodyPart = new MimeBodyPart();
		tempBodyPart.setContent(multipart);

		// 定义一个附件消息
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.setDataHandler(new DataHandler(new FileDataSource(ReceiveSourcePathUtils.sourcePathMap.get("a"))));
		attachment.setFileName(MimeUtility.encodeText("精忠报国.mp3"));

		// 第二次合并，用来添加附件
		MimeMultipart multipart2 = new MimeMultipart();
		multipart2.addBodyPart(tempBodyPart);
		multipart2.addBodyPart(attachment);
		//说明这是以附件的形式
		multipart2.setSubType("mixed");

		// 把复合消息设置到邮件体中
		message.setContent(multipart2);
		// /////////////////
		MailUtils.sendMessage(message);
	}

	public static void attachment_mail() throws Exception {
		Message message = MailUtils.createMessage();
		// ///////////////
		Address from = new InternetAddress("aaa@cab.com");
		Address to = new InternetAddress("bbb@cab.com");

		message.setFrom(from);
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject("包含附件的邮件");

		// 邮件体

		// 定义文本类型的消息
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("这是一个包含附件的邮件", "text/plain;charset=utf-8");

		// 定义一个附件消息
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.setDataHandler(new DataHandler(new FileDataSource(ReceiveSourcePathUtils.sourcePathMap.get("sn"))));
		attachment.setFileName(MimeUtility.encodeText("服装.jpg"));

		// 合并
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(text);
		multipart.addBodyPart(attachment);
		multipart.setSubType("mixed");

		// 把复合消息设置到邮件体中
		message.setContent(multipart);

		// /////////////////
		MailUtils.sendMessage(message);
	}

	public static void html_mail() throws Exception {
		Message message = MailUtils.createMessage();
		// ///////////////
		Address from = new InternetAddress("aaa@cab.com");
		Address to = new InternetAddress("bbb@cab.com");

		message.setFrom(from);
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject("html格式的邮件");

		// 邮件体
		// html文本
		MimeBodyPart content = new MimeBodyPart();
		content.setContent("html的内容<br/><img src='cid:inner_pic'/>",
				"text/html;charset=utf-8");

		// 图片消息
		MimeBodyPart pic = new MimeBodyPart();
		FileDataSource dataSource = new FileDataSource(ReceiveSourcePathUtils.sourcePathMap.get("sn"));
		DataHandler dataHandler = new DataHandler(dataSource);
		pic.setDataHandler(dataHandler);
		pic.setContentID("inner_pic");

		// 合并
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(content);
		multipart.addBodyPart(pic);
		multipart.setSubType("related");

		// 把复合消息设置到邮件体中
		message.setContent(multipart);
		// /////////////////
		MailUtils.sendMessage(message);
		
	}
}
