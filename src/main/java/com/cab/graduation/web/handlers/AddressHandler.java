package com.cab.graduation.web.handlers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cab.graduation.entities.Address;
import com.cab.graduation.entities.User;
import com.cab.graduation.service.AddressService;
import com.cab.graduation.service.CommonService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

@Controller
@RequestMapping("/address")
public class AddressHandler {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AddressService addressService;
	/**
	 * 显示添加页面
	 * @return
	 */
	@RequestMapping(path="/add",method=RequestMethod.GET)
	public String addPage(){
		
		return "client/address/add";
	}
	
	/**
	 * 执行添加操作
	 * @param request
	 * @param address
	 * @return
	 */
	@RequestMapping(path="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Address address){
		User user=(User) request.getSession().getAttribute("user");
		address.setUser(user);
		address.setCreateTime(new Date());
		
		//把新添加的地址自动设为默认地址
		address.setDefaultAddress("1");
		
		//取消之前的默认地址
		addressService.updateDefaultAddressStatusByAddrId(address.getDefaultAddress());
		
		addressService.add(address);
		return "redirect:/address/showListPage/1";
	}
	
	/**
	 * 删除操作
	 * @param addrId
	 * @return
	 */
	@RequestMapping(path="/delete/{addrId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("addrId") Integer addrId){
		Address address=addressService.findAddressById(addrId);
		addressService.delete(address);
		return "redirect:/address/showListPage/1";
	}
	
	/**
	 * 显示更新页面(支持数据回显)
	 * @param addrId
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/update/{addrId}",method=RequestMethod.GET)
	public String update(@PathVariable("addrId") Integer addrId,Map<String,Object> map){
		
		Address address=addressService.findAddressById(addrId);
		map.put("address", address);
		
		return "client/address/edit";
		
	}
	
	/**
	 * 执行更新操作
	 * @param address
	 * @param userId
	 * @return
	 */
	@RequestMapping(path="/update",method=RequestMethod.PUT)
	public String update(Address address,@RequestParam("userId") String userId){
		User user=new User(Integer.valueOf(userId));
		if(address!=null){
			address.setUser(user);
			
			//先把其它默认地址为1的地址记录改为0
			addressService.updateDefaultAddressStatusByAddrId(address.getDefaultAddress());
			
			//再把该地址记录改成1,前提是用户把这个地址修改为默认地址或本身这个地址就是默认地址
			addressService.update(address);
			
		}
		return "redirect:/address/showListPage/1";
	}
	
	/**
	 * 执行查询操作
	 * @param addrId
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/query/{addrId}",method=RequestMethod.GET)
	public String query(@PathVariable("addrId") Integer addrId,Map<String,Address> map){
		Address address=addressService.findAddressById(addrId);
		map.put("address", address);
		return "";
	}
	
	/**
	 * 执行显示当前用户所关联的收获地址的操作
	 * @param request
	 * @param pageNo
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/showListPage/{pageNo}",method=RequestMethod.GET)
	public String showListPage(HttpServletRequest request,@PathVariable("pageNo") Integer pageNo,
							  Map<String,Object> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}
		
		Page<Address> page=new Page<>(pageNo, 3, false);
		String[] params=new String[]{String.valueOf(user.getUserId())};
		Conditions conditions=new Conditions(" where address.user.userId=?",params);
		
		commonService.pageQuery(Address.class, page, conditions);
		
		map.put("pageBean", page);
		return "client/address/list";
		
	}
	
	
}
