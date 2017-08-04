package com.cab.graduation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailboxEntranceUtil {

	private static final String emailLoginConfigPath = MailboxEntranceUtil.class.getResource("/emailloginaddress.properties").getPath();
	private static InputStream in = null;
    private static final Properties prop = new Properties();
    
    public static String getMailboxEntranceURL(String key){
    	try {
            in = new FileInputStream(emailLoginConfigPath);
            prop.load(in);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeQuietly(in);
        }
    	return null;
    }
    
    private static void closeQuietly(AutoCloseable ac){
        if(ac!=null){
            try {
                ac.close();
            } catch (Exception e) {
                //ignore
            }
        }
    }
    
}
