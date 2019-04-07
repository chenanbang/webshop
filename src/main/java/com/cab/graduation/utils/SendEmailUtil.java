package com.cab.graduation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cab.graduation.entities.User;

public class SendEmailUtil {

	//第三方邮件发送平台SendCloud所需要的基本信息
    public static String url = null;
    public static String apiUser = null;
    public static String apiKey = null;

    private static final String sendCloudConfigPath = SendEmailUtil.class.getResource("/sendcloud.properties").getPath();
    private static InputStream in = null;
    private static final Properties prop = new Properties();
    static {
        try {
            in = new FileInputStream(sendCloudConfigPath);
            prop.load(in);

            url = prop.getProperty("url");
            apiUser = prop.getProperty("apiUser");
            apiKey = prop.getProperty("apiKey");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeQuietly(in);
        }
    }
	
	/**
	 * 
	 * @param rcpt_to 邮件接收地址
	 * @param subject 邮件主题
	 * @param html    邮件内容
	 * @throws IOException
	 */
	public static void sendActiveEmail(String rcpt_to,String subject,String html) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", rcpt_to));
        params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
        params.add(new BasicNameValuePair("fromName", "分期商城"));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);

        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 正常返回, 解析返回数据
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.err.println("error");
        }
        httpPost.releaseConnection();
    }
	
	
	public static void main(String[] args) {

		User user = new User();
		user.setUsername("陈安邦");
		user.setActiveCode(UUID.randomUUID().toString());

        String rcpt_to = "17801078412@163.com";
        String subject = "用户激活链接,发送者【WebShop管理员】";
        String html = "Hi @" + user.getUsername() + "!<br/><br/>"
                + "<a href=" + "'http://localhost:8080/webshop/users/confirm_verification/" + user.getActiveCode() + "'>点击链接进行激活操作!</a>"
                + "<br/><hr/><br/>"
                + "如果链接不起作用,你也可以把下面的链接地址直接粘到浏览器地址栏中进行直接访问:<br/>"
                + "<a href=" + "'http://localhost:8080/webshop/users/confirm_verification/" + user.getActiveCode() + "'>http://localhost:8080/webshop/users/confirm_verification/" + user.getActiveCode() + "</a><br/><br/>"
                + "你收到这个邮件是由于你最近创建了一个新的WebShop账号. 如果你没有进行这些操作,请忽略这个邮件,谢谢!";

        try {
        	sendActiveEmail(rcpt_to,subject,html);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
//		String title="你好";
//		String content="<a href='http://www.baidu.com'>百度</a>";
//		
//		String toEmail="peachyx@126.com";
//		
//		sendActiveEmail(title, content, "lisi@webshop.com");
//		
//		sendActiveEmail(title, content, toEmail);
	}
	
	public static void sendActiveEmailOfOld(String title,String content,String toEmail) {
//		HtmlEmail email = new HtmlEmail();
//		email.setHostName("mail.webshop.com");
//		try {
//			email.addTo(toEmail);
//			email.setFrom("admin@webshop.com", "管理员");
//			email.setSubject(title);
//			email.setContent(content, "text/html;charset=utf-8");			
//			email.send();
//		} catch (EmailException e) {
//			throw new RuntimeException("邮件发送异常:"+e);
//		}
	}
//	
//	/**
//	 * 发送的内容是网络资源类型
//	 * @throws EmailException
//	 * @throws MalformedURLException
//	 */
	public static void sendHtmlEmailOfOld() {
//		HtmlEmail email = new HtmlEmail();
//		email.setHostName("mail.webshop.com");
//		email.addTo("zhangsan@webshop.com");
//		email.setFrom("admin@webshop.com", "管理员");
//		email.setSubject("Test email with inline image");
//		  
//		// embed the image and get the content id
//		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
////		URI uri=new File("f:/666.jpg").toURI();
////		URL url=uri.toURL();
//		
//		String cid = email.embed(url, "Apache logo");
//		  
//		// set the html message
//		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
//
//		// set the alternative message
//		email.setTextMsg("Your email client does not support HTML messages");
//
//		// send the email
//		email.send();
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
