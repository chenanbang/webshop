package com.cab.graduation.web.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cab.graduation.dao.impl.CommonDAO;
import com.cab.graduation.entities.Goods;
import com.cab.graduation.entities.User;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

@Controller
public class CommonHandler {

	@Autowired
	private CommonDAO commonDAO;
	
	@RequestMapping("/testCommonDAO")
	public String testCommonDAO(){
		
		Page<User> users=new Page<>(1, 3,false);
		commonDAO.page(User.class, users, new Conditions());
		
		Page<Goods> goods=new Page<>(0, 10,false);
		System.out.println("=========================================");
		commonDAO.page(Goods.class, goods, new Conditions(" where goods.classify.id = ?", new String[]{"1"}));
		
		
		return "success";
	}
}
