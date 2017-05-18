package com.cab.graduation.test;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ReducePicture {
	
	public static void main(String[] args) throws IOException {
		
	Thumbnails.of("F:/2017-04-11_144806.png")
				  .size(190, 74)
				  .keepAspectRatio(false)
				  .toFile("F:/wanjiasong.png");
		System.out.println("successful");
		
//		File file=new File("F:/img/vegetables");
//		if(file.isDirectory()){
//			File[] files=file.listFiles();
//			for(File f:files){
//				System.out.println(f.toString());
//				Thumbnails.of(f.toString())
//				  .size(190, 170)
//				  .keepAspectRatio(false)
//				  .toFile(f.toString());
//				System.out.println("successful");
//			}
//		}
	}
}
