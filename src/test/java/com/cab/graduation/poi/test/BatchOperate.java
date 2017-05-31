package com.cab.graduation.poi.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cab.graduation.dao.GoodsDAO;
import com.cab.graduation.entities.Classify;
import com.cab.graduation.entities.Goods;
import com.cab.graduation.utils.ImageDisposeUtils;
import com.cab.graduation.utils.WebShopUtils;
import com.cab.graduation.web.handlers.GoodsHandlers;

public class BatchOperate {

	private static ApplicationContext ctx=null;
	private static SessionFactory sessionFactory=null;
	static {
		
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory=ctx.getBean(SessionFactory.class);
		
	}
	
	
	
	private static final String imgConfigFilePath;
	private static final Properties props=new Properties();
	private static InputStream inStream=null;
	static {
		
		imgConfigFilePath=GoodsHandlers.class.getResource("/imgBasePath.properties").getPath();
		try {
			inStream=new FileInputStream(imgConfigFilePath);
			props.load(inStream);
//			System.out.println(props.getProperty("basePath"));
		} catch (IOException e) {
			throw new RuntimeException("文件加载异常:"+e);
		}finally{
			WebShopUtils.closeQuietly(inStream);
		}
	}
	
	
	@Test
	public void getSession(){
		System.out.println(sessionFactory.getCurrentSession());
		//return sessionFactory.getCurrentSession();
	}
	
	@Test
	public void executeBatchOperator() throws IOException{
		NPOIFSFileSystem fs=new NPOIFSFileSystem(new File("C:/Users/Administrator/Desktop/goods_info.xls"));
		Workbook wb=new HSSFWorkbook(fs.getRoot(), true);
		DataFormatter formatter=new DataFormatter();
		
		StringBuilder sb=new StringBuilder();
		Goods goods=new Goods();
		for(int i=0;i<wb.getNumberOfSheets();i++){
			Sheet sheet=wb.getSheetAt(i);
			for(int j=1;j<sheet.getPhysicalNumberOfRows();j++){
				Row row=sheet.getRow(j);
				for(Cell cell : row){
					String cellContent=formatter.formatCellValue(cell);
					sb.append(cellContent).append("-");
					
				}
				
				String[] cellContents=sb.toString().split("-");
//				System.out.println(Arrays.toString(cellContents));
				
				//将读取的行信息封装到Goods对象中，然后进行持久化操作
				goods.setGoodName(cellContents[0]);
				goods.setGoodPrimePrice(Double.valueOf(cellContents[1]));
				goods.setGoodSpikePrice(Double.valueOf(cellContents[2]));
				goods.setGoodStore(Integer.valueOf(cellContents[3]));
				goods.setGoodSimpleDesc(cellContents[4]);
				goods.setGoodDescribe(cellContents[5]);
				
				File file=new File(cellContents[6]);
				//FileInputStream imgStream=new FileInputStream(file);
				
				//商品图片要往两个地方上传：①部署项目的位置  ②项目本身的位置 
				String serverUploadfileBasePath="F:/eclipseforjavaweb/EclipseProjects/GraduationProject/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/webshop/";
				String uploadfileBasePath=props.getProperty("basePath");
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				Date date=new Date();
				String[] strs=sdf.format(date).split("-");
				StringBuilder sbPath=new StringBuilder();
				for(String s : strs){
					sbPath.append("/").append(s);
				}
				
				//分别往服务器和硬盘上的指定位置保存图片,先判断要保存图片的位置(文件夹)是否存在,不存在则创建之
				String goodPath="/uploadfile/goodsImages"+sbPath.toString();
				String goodDetailPath="/uploadfile/goodsDetailImages"+sbPath.toString();
				String goodSmallPath="/uploadfile/goodsSmallImages"+sbPath.toString();
				
				String[] goodImgPaths={serverUploadfileBasePath+goodPath,
									   serverUploadfileBasePath+goodDetailPath,
									   serverUploadfileBasePath+goodSmallPath,
									   uploadfileBasePath+goodPath,
									   uploadfileBasePath+goodDetailPath,
									   uploadfileBasePath+goodSmallPath};
				
				for(String goodImgPath : goodImgPaths){
					File goodImgFile=new File(goodImgPath);
					if(!goodImgFile.exists()){
						goodImgFile.mkdirs();
					}
				}
				
				String newFilename=date.getTime()+UUID.randomUUID().toString()+file.getName();
				
				//准备往数据库中保存的部分路径
				goodPath="/uploadfile/goodsImages"+sbPath.toString()+"/"+newFilename;
				goodDetailPath="/uploadfile/goodsDetailImages"+sbPath.toString()+"/"+newFilename;
				goodSmallPath="/uploadfile/goodsSmallImages"+sbPath.toString()+"/"+newFilename;
				
				//开始往服务器上保存
				ImageDisposeUtils.handle(new FileInputStream(file), 190, 170, serverUploadfileBasePath+goodPath);
				ImageDisposeUtils.handle(new FileInputStream(file), 300, 300, serverUploadfileBasePath+goodDetailPath);
				ImageDisposeUtils.handle(new FileInputStream(file), 60, 60, serverUploadfileBasePath+goodSmallPath);
				
				//开始往硬盘上保存
				ImageDisposeUtils.handle(new FileInputStream(file), 190, 170, uploadfileBasePath+goodPath);
				ImageDisposeUtils.handle(new FileInputStream(file), 300, 300, uploadfileBasePath+goodDetailPath);
				ImageDisposeUtils.handle(new FileInputStream(file), 60, 60, uploadfileBasePath+goodSmallPath);
				
				goods.setGoodPath(goodPath);
				goods.setGoodDetailPath(goodDetailPath);
				goods.setGoodSmallPath(goodSmallPath);
				
				//设置商品的类别
				goods.setClassify(new Classify(Integer.valueOf(cellContents[7])));
				
				//设置添加商品的时间
				goods.setDatetime(new Date());
				
				//设置是否软删除，默认为false
				goods.setDel(0);
				
				//getSession().save(goods);
				
				//StringBuilder每装载一行后就将其初始化
				sb.delete(0, sb.length());
			}
		}
		
		
		
		WebShopUtils.closeQuietly(fs);
		
		
	}
	
}
