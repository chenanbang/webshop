package com.cab.graduation.mail.test;

import com.cab.graduation.utils.ReceiveSourcePathUtils;

public class Test{
	
	public static void main(String[] args){
		
		System.out.println("hahah");
		
		String str="/uploadfile/goodsSmallImages/2017/04/25/16/01/16/1493107276910bigPic5f3622b8-028a-4e62-a77f-f41a16d715ed.jpg";
		int end=str.lastIndexOf("/");
		System.out.println(end);
		System.out.println(str.substring(0,end+1));
		
		
	}
}
