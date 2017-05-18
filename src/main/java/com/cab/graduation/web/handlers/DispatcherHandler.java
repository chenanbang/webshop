package com.cab.graduation.web.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cab.graduation.entities.User;

@Controller
public class DispatcherHandler {

	@RequestMapping(path="/index")
	public String index(){
		
		return "forward:/goods/listProduct";
//		return "redirect:/goods/listProduct";
		
	}
	
	@RequestMapping(path="/goodDetail")
	public String goodDetail(){
		
		return "goodDetail";
	}
	
	@RequestMapping(path="/showOrder")
	public String showOrder(){
		
		return "order";
	}
	
	
	@RequestMapping(path="/manageCenter")
	public String manageCenter(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRank()==1){
				return "admin/home";
			}
		}
		return "admin/login";
	}
	
	@RequestMapping(path="/goManageCenter")
	public String goManageCenter(){
		return "admin/home";
		
	}
	
	@RequestMapping(path="/userCenter")
	public String userCenter(HttpServletRequest request,Map<String,String> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}else{
			if(user.getRank()==1){
				map.put("message", "请用普通用户登录后再进行相关操作!");
				return "forward:/login";
			}
		}
		return "client/home";
		
	}
	
	
	@RequestMapping(path="/addressManageCenter")
	public String addressManageCenter(HttpServletRequest request,Map<String,String> map){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}
		
		return "client/address/addressManage";
	}
	
	@RequestMapping(path="/addReceiveAddress")
	public String addReceiveAddress(HttpServletRequest request,Map<String,String> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}
		return "client/address/addReceiveAddress";
	}
	
	@RequestMapping(path="/goEditSuccessPage")
	public String goEditSuccessPage(){
		
		return "client/user/editSuccess";
	}
	
	
	@RequestMapping(path="/lookOrders")
	public String lookOrders(HttpServletRequest request,Map<String,String> map){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}
		return "client/order/orderManage";
	}
	
	
//	@RequestMapping(path="/showTop")
//	public String showTop(){
//		
//		return "admin/top";
//	}
//	
//	@RequestMapping(value="/showBottom")
//	public String showBottom(){
//		
//		return "admin/bottom";
//	}
//	
//	@RequestMapping(path="/showLeft")
//	public String showLeft(){
//		
//		return "admin/left";
//	}
//	
//	@RequestMapping(path="/showWelcome")
//	public String showWelcome(){
//		
//		return "admin/welcome";
//	}
	
}
