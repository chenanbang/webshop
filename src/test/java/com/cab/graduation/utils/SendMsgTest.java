package com.cab.graduation.utils;

import java.io.IOException;

import org.junit.Test;

public class SendMsgTest {

	@Test
	public void test() {
		ReadWebChineseUtil.smsMob="17801078412";
		ReadWebChineseUtil.smsText="验证码为:"+"652433";
		try {
			SendMsgUtil.sendMsg();
		} catch (IOException e) {
			throw new RuntimeException("IO异常:"+e);
		}
	}

}
