package com.cab.graduation.web.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cab.graduation.entities.User;

@Controller
public class FileUpload {
	
	@RequestMapping("/showUploadPage")
	public String showUploadPage(){
		
		return "upload";
	}
	
//	键	值
//	Content-Type	multipart/form-data; boundary=---------------------------7e129d2a16126e
	@RequestMapping(value="/upload")
	public String upload(User user, @RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest request,Map<String,String> map){
		System.out.println(user);
		System.out.println("开始。。。");
		String path=request.getSession().getServletContext().getRealPath("uploadfile");
		System.out.println(path);
		String fileName=file.getOriginalFilename();
		System.out.println("文件名:"+fileName);
		System.out.println("文件类型:"+file.getContentType());
		
		map.put("fileUrl", request.getContextPath()+"/uploadfile/"+fileName);
		return "success";
		
	}
}
