package com.cab.graduation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cab.graduation.utils.ReadWebChineseUtil;

/**
 * 获取资源文件的path,并将其结果放到一个map中
 * @author chenanbang
 *
 */
public class ReceiveSourcePathUtils {

	public static final Map<String, String> sourcePathMap=new HashMap<>();
	
	static{
		InputStream inStream=null;
		try {
			inStream=new FileInputStream(ReadWebChineseUtil.class.getResource("/resourceName.properties").getPath());
			Properties props=new Properties();
			props.load(inStream);
			
			for(Object key : props.keySet()){
				sourcePathMap.put((String)key, ReceiveSourcePathUtils.class.getResource("/"+(String)props.get(key)).getPath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("IO异常:"+e);
		}finally {
			WebShopUtils.closeQuietly(inStream);
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
