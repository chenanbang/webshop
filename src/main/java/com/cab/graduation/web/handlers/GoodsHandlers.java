package com.cab.graduation.web.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cab.graduation.entities.dto.Classify;
import com.cab.graduation.entities.dto.Goods;
import com.cab.graduation.service.ClassifyService;
import com.cab.graduation.service.GoodsService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.ImageDisposeUtils;
import com.cab.graduation.utils.Page;
import com.cab.graduation.utils.WebShopUtils;

@Controller
@RequestMapping("/goods")
public class GoodsHandlers {

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
	
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ClassifyService classifyService;
	
	@RequestMapping(path="/showAddPage")
	public String showAddPage(com.cab.graduation.entities.Goods goods,Map<String,List<Classify>> map){
		List<Classify> classifyList=classifyService.list();
		map.put("classifyList", classifyList);
		System.out.println(goods);
		return "admin/goods/add";
	}
	
	/**
	 * @param classifyId
	 * @param request
	 * @param goods
	 * @param file
	 * @return
	 */
	@RequestMapping(path="/saveOrUpdate",method=RequestMethod.POST)
	public String saveOrUpdate(com.cab.graduation.entities.Goods goods,HttpServletRequest request,
							   @RequestParam("file") MultipartFile file,Map<String,Object> map){
		
		Integer goodId=goods.getGoodId();
		
		String uploadfileBasePath=props.getProperty("basePath");
		
		String serverUploadfileBasePath=request.getServletContext().getRealPath("/");
		//serverUploadfileBasePath得到的结果为  F:\eclipseforjavaweb\EclipseProjects\GraduationProject\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\webshop\
		serverUploadfileBasePath=serverUploadfileBasePath.substring(0, serverUploadfileBasePath.length()-1);
		
		//商品添加业务
		if(goodId==null){
			
			if(file.isEmpty()){
				map.put("message", "文件表单不能为空!");
				return "redirect:/goods/showAddPage";
			}else{
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
					Date date=new Date();
					String[] strs=sdf.format(date).split("-");
					StringBuilder sb=new StringBuilder();
					for(String s : strs){
						sb.append("/").append(s);
					}
					
					//分别往服务器和硬盘上的指定位置保存图片,先判断要保存图片的位置(文件夹)是否存在,不存在则创建之
					String goodPath="/uploadfile/goodsImages"+sb.toString();
					String goodDetailPath="/uploadfile/goodsDetailImages"+sb.toString();
					String goodSmallPath="/uploadfile/goodsSmallImages"+sb.toString();
					
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
					
					String newFilename=date.getTime()+file.getOriginalFilename();
					
					//准备往数据库中保存的部分路径
					goodPath="/uploadfile/goodsImages"+sb.toString()+"/"+newFilename;
					goodDetailPath="/uploadfile/goodsDetailImages"+sb.toString()+"/"+newFilename;
					goodSmallPath="/uploadfile/goodsSmallImages"+sb.toString()+"/"+newFilename;
					
					//开始往服务器上保存
					ImageDisposeUtils.handle(file.getInputStream(), 190, 170, serverUploadfileBasePath+goodPath);
					ImageDisposeUtils.handle(file.getInputStream(), 300, 300, serverUploadfileBasePath+goodDetailPath);
					ImageDisposeUtils.handle(file.getInputStream(), 60, 60, serverUploadfileBasePath+goodSmallPath);
					
					//开始往硬盘上保存
					ImageDisposeUtils.handle(file.getInputStream(), 190, 170, uploadfileBasePath+goodPath);
					ImageDisposeUtils.handle(file.getInputStream(), 300, 300, uploadfileBasePath+goodDetailPath);
					ImageDisposeUtils.handle(file.getInputStream(), 60, 60, uploadfileBasePath+goodSmallPath);
					
					goods.setGoodPath(goodPath);
					goods.setGoodDetailPath(goodDetailPath);
					goods.setGoodSmallPath(goodSmallPath);
					if(goodId==null){
						goods.setDatetime(new Date());
					}
				} catch (IOException e) {
					throw new RuntimeException("图片处理异常:"+e);
				}
			}
			//执行添加操作
			goodsService.saveOrUpdate(goods);
			
			return "redirect:/goods/showListPage/1";
		}
		
		//商品修改业务,这里的修改主要是修改图片相关的信息和内容
		else{
			if(!file.isEmpty()){
				try{
					
					String goodPath=goods.getGoodPath();
					String goodDetailPath=goods.getGoodDetailPath();
					String goodSmallPath=goods.getGoodSmallPath();
					
					//在修改图片相关信息前想删掉之前图片的相关信息,避免无用的图片占用服务器的磁盘空间
					String[] imgPaths={serverUploadfileBasePath+goodPath,
									   serverUploadfileBasePath+goodDetailPath,
									   serverUploadfileBasePath+goodSmallPath,
									   uploadfileBasePath+goodPath,
									   uploadfileBasePath+goodDetailPath,
									   uploadfileBasePath+goodSmallPath};
					
					for(String imgPath : imgPaths){
						File imgFile=new File(imgPath);
						if(imgFile.exists()){
							imgFile.delete();
						}
					}
					
					Date date=new Date();
					String newFilename=date.getTime()+file.getOriginalFilename();
					
					//准备往数据库中保存的部分路径
					goodPath=goods.getGoodPath().substring(0, goods.getGoodPath().lastIndexOf("/")+1)+newFilename;
					goodDetailPath=goods.getGoodDetailPath().substring(0, goods.getGoodDetailPath().lastIndexOf("/")+1)+newFilename;
					goodSmallPath=goods.getGoodSmallPath().substring(0, goods.getGoodSmallPath().lastIndexOf("/")+1)+newFilename;
					
					//开始往服务器上保存
					ImageDisposeUtils.handle(file.getInputStream(), 190, 170, serverUploadfileBasePath+goodPath);
					ImageDisposeUtils.handle(file.getInputStream(), 300, 300, serverUploadfileBasePath+goodDetailPath);
					ImageDisposeUtils.handle(file.getInputStream(), 60, 60, serverUploadfileBasePath+goodSmallPath);
					
					//开始往硬盘上保存
					ImageDisposeUtils.handle(file.getInputStream(), 190, 170, uploadfileBasePath+goodPath);
					ImageDisposeUtils.handle(file.getInputStream(), 300, 300, uploadfileBasePath+goodDetailPath);
					ImageDisposeUtils.handle(file.getInputStream(), 60, 60, uploadfileBasePath+goodSmallPath);
					
					goods.setGoodPath(goodPath);
					goods.setGoodDetailPath(goodDetailPath);
					goods.setGoodSmallPath(goodSmallPath);
					
				}catch (IOException e) {
					throw new RuntimeException("图片处理异常:"+e);
				}
			}
			//执行修改操作
			goodsService.saveOrUpdate(goods);
			
			return "redirect:/goods/showListPage/1";
		}
	}
	
	@RequestMapping(path="/listProduct")
	public String listProduct(Map<String,List<Goods>> map){
		List<Goods>goodsList=goodsService.listProduct();
		map.put("goodsList", goodsList);
		for(Goods g : goodsList){
			System.out.println(g.getGoodName());
		}
		System.out.println("======================");
		goodsList=goodsService.findGoodsByTime();
		map.put("goodsListOrderByTime", goodsList);
		for(Goods g : goodsList){
			System.out.println(g.getGoodName());
		}
		return "index";
	}
	
	/**
	 * 商品分类展示接口
	 * @param classifyId
	 * @param pageNo
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/goodsClassify/{classifyId}/{pageNo}")
	public String goodsClassify(@PathVariable("classifyId")Integer classifyId,@PathVariable("pageNo")Integer pageNo,Map<String,Object> map){
		
		Page<com.cab.graduation.entities.Goods> page=new Page<>(pageNo, 12,false);
		Conditions conditions=new Conditions(" where goods.classify.id = ?", new String[]{String.valueOf(classifyId)});
		goodsService.pageQuery(com.cab.graduation.entities.Goods.class, page, conditions);
		map.put("pageBean", page);
		map.put("classifyId", classifyId);
		return "goodsClassify";
	}
	
	/**
	 * 商品管理展示接口
	 * @param pageNo
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/showListPage/{pageNo}")
	public String showListPage(@PathVariable("pageNo")Integer pageNo,Map<String,Page> map){
		//List<Goods>goodsList=goodsService.list();
//		map.put("goodsList", goodsList);
		Page<com.cab.graduation.entities.Goods> page=new Page<>(pageNo, 6,true);
		Conditions conditions=new Conditions(" where goods.del=?", new String[]{"0"});
		goodsService.pageQuery(com.cab.graduation.entities.Goods.class, page, conditions);
		map.put("pageBean", page);
		return "admin/goods/list";
	}
	
	@RequestMapping(path="/showGoodsEdit/{id}")
	public String showGoodsEdit(@PathVariable("id") Serializable id,Map<String,Object> map){
		com.cab.graduation.entities.Goods goods=goodsService.findGoodsById(id);
		map.put("goods", goods);
		List<Classify> classifyList=classifyService.list();
		map.put("classifyList", classifyList);
		return "admin/goods/edit";
	}
	
	@RequestMapping(path="/executeDelete/{id}")
	public String executeDelete(@PathVariable("id") Serializable id){
		com.cab.graduation.entities.Goods goods=goodsService.findGoodsById(id);
//		goodsService.delete(goods);
		goods.setDel(1);
		goodsService.saveOrUpdate(goods);
		return "redirect:/goods/showListPage/1";
	}
	
	@RequestMapping(path="/showGoodsDetail/{id}")
	public String showGoodsDetail(@PathVariable("id") Integer id,Map<String,com.cab.graduation.entities.Goods> map){
		com.cab.graduation.entities.Goods goods = goodsService.findGoodsById(id);
		map.put("goods", goods);
		return "goodDetail";
	}
	
}
