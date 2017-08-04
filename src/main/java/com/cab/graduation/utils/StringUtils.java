package com.cab.graduation.utils;

public class StringUtils {

	/**
	 * 根据邮箱登陆地址来获取其独特的部分,如:
	 * ① http://mail.163.com/ =》163
	 * ② http://mail.126.com/ =》126
	 * ③ http://mail.sina.com.cn/ =》sina
	 * ④ https://mail.qq.com/ =》qq
	 * ⑤ https://mail.sohu.com/ =》sohu
	 * @param mailboxEntranceURL 邮箱登陆 url
	 * @return
	 */
	public static String getEmailKeyByMailboxEntranceURL(String mailboxEntranceURL){
		return mailboxEntranceURL.replace('.', '-').split("-")[1];
	}
	
	/**
	 * 
	 * @param emailAddress 邮箱地址  如: 17801078412@163.com
	 * @return
	 */
	public static String getEmailKeyByEmailAddress(String emailAddress){
		emailAddress = emailAddress.substring(emailAddress.indexOf("@")+1);
		return emailAddress.replace('.', '-').split("-")[0];
		
	}
	
}
