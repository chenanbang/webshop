package com.cab.graduation.test;

import java.util.ArrayList;
import java.util.List;

public class IteratorTest {

	public static void main(String[] args) {
		List<String> list=new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		
		for(int i=0;i<list.size();i++){
			String result=list.get(i);
			System.out.println("result:"+result);
			System.out.println("i:"+i);
			if(i==2){
				String removeElement=list.remove(i);
				System.out.println("removeElement:"+removeElement);
			}
		}
		
		for(int i=0;i<list.size();i++){
			String result=list.get(i);
			System.out.println("result:"+result);
			
		}
		
		/*String regex="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		
		String email="17SF2@163.com";
		boolean result=email.matches(regex);
		System.out.println(result);*/
	}
	
}
