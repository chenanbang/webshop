package com.cab.graduation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.impl.CommonDAO;
import com.cab.graduation.service.CommonService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	public long queryCountWidthConditions(Class clazz,Conditions conditions) {
		return commonDAO.queryCountWidthConditions(clazz, conditions);
	}
	
	@Override
	public <T> void pageQuery(Class clazz,Page<T> page,Conditions conditions){
		
		List<T> tList=commonDAO.page(clazz, page, conditions);
		
		long totalSize = commonDAO.queryCountWidthConditions(clazz, conditions);
		Integer totalPage=(int) (totalSize/page.getPageSize());
		if(totalSize%page.getPageSize()!=0){
			totalPage++;
		}
		page.setTotalSize((int)totalSize);
		page.setTotalPage(totalPage);
		page.setItems(tList);
		
	}

}
