package com.cab.graduation.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.GoodsDAO;
import com.cab.graduation.dao.impl.CommonDAO;
import com.cab.graduation.entities.dto.Goods;
import com.cab.graduation.service.GoodsService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private GoodsDAO goodsDAO;

	@Override
	public void saveOrUpdate(com.cab.graduation.entities.Goods goods) {
		goodsDAO.saveOrUpdate(goods);
	}
	
	@Override
	public void delete(com.cab.graduation.entities.Goods goods) {
		goodsDAO.delete(goods);
	}
	
	@Override
	public List<Goods> listProduct() {
		List<Goods> result = new ArrayList<>();
		List<com.cab.graduation.entities.Goods> goodsList = goodsDAO.list();
//		commonDAO.
		for (com.cab.graduation.entities.Goods good : goodsList) {
			result.add(new Goods(good));
		}
		return result;
	}

	@Override
	public List<Goods> findGoodsByTime() {
		List<Goods> result = new ArrayList<>();
		List<com.cab.graduation.entities.Goods> goodsList = goodsDAO.findGoodsByTime();
		for (com.cab.graduation.entities.Goods good : goodsList) {
			result.add(new Goods(good));
		}

		return result;
	}

	@Override
	public com.cab.graduation.entities.Goods findGoodsById(Serializable id) {
		return goodsDAO.findGoodsById(id);
	}
	
	@Override
	public List<Goods> list() {
		List<Goods> result = new ArrayList<>();
		List<com.cab.graduation.entities.Goods> goodsList = goodsDAO.list();
		for (com.cab.graduation.entities.Goods good : goodsList) {
			//System.out.println(good.getClassify().getCategory());
//			result.add(new Goods(good));
			result.add(new Goods(good,good.getClassify().getCategory()));
		}

		return result;
	}

	@Override
	public void pageQuery(Class clazz, Page page, Conditions conditions) {
		List<Goods> resultList=new ArrayList<>();
		List<com.cab.graduation.entities.Goods> goodsList=commonDAO.page(clazz, page, conditions);
		
		//通过看是否要跳转到管理页面来封装商品到对应的dto构造方法中
		if(page.getShowGoodsManagePage()){
			for (com.cab.graduation.entities.Goods good : goodsList) {
				System.out.println(good.getClassify().getCategory());
				//resultList.add(new Goods(good));
				resultList.add(new Goods(good,good.getClassify().getCategory()));
			}
		}else{
			for(com.cab.graduation.entities.Goods goods : goodsList){
				resultList.add(new Goods(goods));
			}
		}
		
		long totalSize = commonDAO.queryCountWidthConditions(clazz, conditions);
		Integer totalPage=(int) (totalSize/page.getPageSize());
		if(totalSize%page.getPageSize()!=0){
			totalPage++;
		}
		page.setTotalSize((int)totalSize);
		page.setTotalPage(totalPage);
		page.setItems(resultList);
		
	}
	
}
