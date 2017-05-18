package com.cab.graduation.web.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cab.graduation.entities.User;

@Controller
public class TestJsonHandler {

	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(){
		return "helloworld! " + new Date();
	}
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<User> testJson(){
		
		List<User> users=new ArrayList<>();
		users.add(new User(1,"张三",23));
		users.add(new User(2,"李四",32));
		users.add(new User(3,"王老五",33));
		users.add(new User(4,"赵六",8));
		
		return users;
	
	}
	
	@RequestMapping("/basePath")
	public String testBasePath(HttpServletRequest request){
		
		String basePath=request.getServletContext().getRealPath("uploadfile");
		System.out.println(basePath);
		System.out.println(request.getServletContext().getRealPath(""));
		System.out.println(request.getServletContext().getRealPath("/"));
		
		
		
		return "";
	}
	
	public static void main(String[] args) {
		String str="F:/eclipseforjavaweb/webshop/";
		System.out.println(str.substring(0,str.length()-1));
	
	}
	
}
