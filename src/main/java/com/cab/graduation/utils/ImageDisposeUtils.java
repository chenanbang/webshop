package com.cab.graduation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;

public class ImageDisposeUtils {
	
	public static void handle(InputStream inStream,int width,int height,String destination){
		
		try {
			
			Thumbnails.of(inStream)
					  .size(width, height)
					  .keepAspectRatio(false)
					  .toFile(destination);
			
		} catch (IOException e) {
			throw new RuntimeException("图片处理异常:"+e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {

	}
	
}
