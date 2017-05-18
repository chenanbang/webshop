package com.cab.graduation.web.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cab.graduation.utils.VerifyCodeUtils;

@Controller
public class AuthImageHandler {
	
	@RequestMapping(path="/getAuthImage")
	public void getAuthImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//生成随机字符串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//存入会话session中
		HttpSession session=request.getSession();
		session.setAttribute("randAuthCode", verifyCode.toLowerCase());
		//生成图片
		int w = 120, h=40;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
	
	@RequestMapping(value="/getAuthCodeFromSession")
	public void getAuthCodeFromSession(HttpSession session,HttpServletResponse response) throws IOException{
		String authCode=(String) session.getAttribute("randAuthCode");
		response.getWriter().print(authCode);
	}
}
