package com.cab.graduation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.ClassifyDAO;
import com.cab.graduation.entities.dto.Classify;
import com.cab.graduation.service.ClassifyService;

@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {

	@Autowired
	private ClassifyDAO classifyDAO;
	
	@Override
	public List<Classify> list() {
		List<Classify> classifyList=new ArrayList<>();
		List<com.cab.graduation.entities.Classify> classifies = classifyDAO.list();
		for(com.cab.graduation.entities.Classify classify : classifies){
			classifyList.add(new Classify(classify));
		}
		return classifyList;
	}

}
