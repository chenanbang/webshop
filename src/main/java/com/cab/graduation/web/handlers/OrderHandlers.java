package com.cab.graduation.web.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cab.graduation.entities.Address;
import com.cab.graduation.entities.Goods;
import com.cab.graduation.entities.Order;
import com.cab.graduation.entities.OrderItem;
import com.cab.graduation.entities.User;
import com.cab.graduation.entities.dto.Cart;
import com.cab.graduation.service.AddressService;
import com.cab.graduation.service.CartService;
import com.cab.graduation.service.CommonService;
import com.cab.graduation.service.OrderItemService;
import com.cab.graduation.service.OrderService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;
import com.cab.graduation.utils.WebShopUtils;

@Controller
@RequestMapping(path="/order")
public class OrderHandlers {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(path="/generateOrder")
	public String generateOrder(@RequestParam(value="cIds",required=false) String cIds,@RequestParam(value="goodIds",required=false) String goodIds,
			@RequestParam(value="oiNums",required=false) String oiNums,@RequestParam(value="oiSubTotals",required=false) String oiSubTotals,
			@RequestParam(value="totalPrice",required=false) String orderTotalPrice,@RequestParam(value="smallImagePaths",required=false) String smallImagePaths,
			@RequestParam(value="goodDescribes",required=false) String goodDescribes,@RequestParam(value="goodPrices",required=false) String goodPrices,
			HttpServletRequest request,Map<String,Object> map){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "您还没有登录,请登录后再进行相关操作!");
			return "redirect:/login";
		}
		
		if(!WebShopUtils.hasEmpty(cIds,oiNums,goodIds,oiSubTotals)){
			
			//订单信息
			map.put("cIds", cIds);
			map.put("goodIds", goodIds);
			map.put("oiNums", oiNums);
			map.put("oiSubTotals", oiSubTotals);
			
			String[] cIdsArray=cIds.split(",");
			String[] goodIdsArray=goodIds.split(",");
			String[] oiNumsArray=oiNums.split(",");
			String[] oiSubTotalsArray=oiSubTotals.split(",");
			String[] smallImagePathsArray=smallImagePaths.split(",");
			String[] goodDescribesArray=goodDescribes.split(",");
			String[] goodPricesArray=goodPrices.split(",");
			
			//将订单信息放到List集合中方便在页面上遍历
			List<Cart> carts=new ArrayList<>();
			for(int i=0;i<cIdsArray.length;i++){
				Cart cart=new Cart();
				//订单相关信息
				cart.setcId(Integer.valueOf(cIdsArray[i]));
				cart.setGoodId(Integer.valueOf(goodIdsArray[i]));
				cart.setcNum(Integer.valueOf(oiNumsArray[i]));
				cart.setSubTotalPrice(Double.valueOf(oiSubTotalsArray[i]));
				
				//显示相关信息
				cart.setSmallImagePath(smallImagePathsArray[i]);
				cart.setGoodDescribe(goodDescribesArray[i]);
				cart.setGoodPrice(Double.valueOf(goodPricesArray[i]));
				carts.add(cart);
			}
			
			Address addr=addressService.findAddressByUserIdAnddefaultAddressStatus(user.getUserId(),"1");
			
			//防止用户刚注册后由于没有添加收货地址而导致的 Integer.valueOf(addrId) 类型转换异常
			if(addr==null){
				addr=new Address();
				addr.setAddrId(0);
			}
			
			//订单信息
			map.put("carts", carts);
			
			//地址信息
			map.put("address", addr);
			
			//总计
			map.put("orderTotalPrice", orderTotalPrice);
			
			return "admin/order/confirmOrder";
			
		}else{
			return "redirect:/cart/showMyCarts";
		}
	}
	
	@RequestMapping(path="/submitOrder")
	public String submitOrder(@RequestParam(value="cIds",required=false) String cIds,@RequestParam(value="goodIds",required=false) String goodIds,
			@RequestParam(value="oiNums",required=false) String oiNums,@RequestParam(value="oiSubTotals",required=false) String oiSubTotals,Map<String,String> map,
			@RequestParam(value="addrId",required=false) String addrId,@RequestParam(value="totalPrice",required=false) String orderTotalPrice,HttpServletRequest request){
		
		if(!WebShopUtils.hasEmpty(oiNums,goodIds,oiSubTotals,orderTotalPrice,addrId)){
			
			String[] goodIdsArray=goodIds.split(",");
			String[] oiNumsArray=oiNums.split(",");
			String[] oiSubTotalsArray=oiSubTotals.split(",");
			
			Order order=new Order();
			Set<OrderItem> orderItems=new HashSet<>();
			for(int i=0;i<goodIdsArray.length;i++){
				OrderItem orderItem=new OrderItem(Integer.valueOf(oiNumsArray[i]), Double.valueOf(oiSubTotalsArray[i]));
				orderItem.setGoods(new Goods(Integer.valueOf(goodIdsArray[i])));
				orderItem.setOrder(order);
				orderItems.add(orderItem);
			}
			User user=(User) request.getSession().getAttribute("user");
			order.setOrderNo(WebShopUtils.generateOrderSerialNum());
			order.setOrderTotalPrice(Double.valueOf(orderTotalPrice));
			order.setOrderCreateTime(new Date());
			order.setIsPay("0");
			order.setOrderStatus(0);
			order.setDel(0);
			order.setUser(user);
			order.setAddress(new Address(Integer.valueOf(addrId)));
			order.setOrderItems(orderItems);
			
			orderService.save(order);
			for(OrderItem orderItem : order.getOrderItems()){
				orderItemService.save(orderItem);
			}
	
			if(!"".equals(cIds) && cIds!=null){
				String[] cIdsArray=cIds.split(",");
				for(String cId : cIdsArray){
					com.cab.graduation.entities.Cart cart=cartService.findCartById(Integer.valueOf(cId));
					cartService.deleteCart(cart);
				}
			}
			
			map.put("message", "订单已提交,感谢您的大力支持!");
			return "forward:/index";
			
		}else{
			return "redirect:/cart/showMyCarts";
		}
	}
	
	@RequestMapping(path="/delete/{forward}/{orderId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("orderId") Integer orderId,
						 @PathVariable("forward") String forward){
		Order order=orderService.findOrderById(orderId);
//		orderService.delete(order);
		
		order.setDel(1);
		orderService.update(order);
		
		if("all".equals(forward)){
			return "redirect:/order/listOrders/1";
			
		}
		if("pay".equals(forward)){
			return "redirect:/order/listIsOrNotPayOrders/1/1";
			
		}
		if("notPay".equals(forward)){
			return "redirect:/order/listIsOrNotPayOrders/1/0";
			
		}
		if("send".equals(forward)){
			return "redirect:/order/listIsOrNotSendOrders/1/1";
			
		}
			
		return "redirect:/order/listIsOrNotSendOrders/1/0";
			
	}
	
	//执行发货操作
	//只有加了@ResponseBody注解的方法才能返回字符串格式的数据后JSON格式的数据
	@ResponseBody
	@RequestMapping(path="/updateOrderStatus/{orderId}",method=RequestMethod.POST)
	public String updateOrderStatus(@PathVariable(value="orderId",required=false) Integer orderId){

		Order order=orderService.findOrderById(orderId);
		order.setOrderStatus(1);
		orderService.update(order);
		
		return String.valueOf(order.getOrderStatus());
		
	}
	
	//执行付款操作
	@ResponseBody
	@RequestMapping(path="/updateOrderPayState/{orderId}",method=RequestMethod.POST)
	public String updateOrderPayState(@PathVariable(value="orderId",required=false) Integer orderId){
		Order order=orderService.findOrderById(orderId);
		order.setIsPay("1");
		orderService.update(order);
		
		return order.getIsPay();
		
	}
	
	
	@RequestMapping(path="/listOrders/{pageStartNum}",method=RequestMethod.GET)
	public String listOrders(@PathVariable("pageStartNum") Integer pageStartNum,Map<String,Page<Order>> map){
		
		Page<Order> page=new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=?", new String[]{"0"});
		commonService.pageQuery(Order.class, page, conditions);
		
		map.put("pageBean", page);
		
		return "admin/order/list";
		
	}
	
	/**
	 * 用来查询未付款或已付款的订单
	 * @param pageStartNum
	 * @param map
	 * @param isPay
	 * @return
	 */
	@RequestMapping(path="/listIsOrNotPayOrders/{pageStartNum}/{isPay}",method=RequestMethod.GET)
	public String listIsOrNotPayOrders(@PathVariable("pageStartNum") Integer pageStartNum,Map<String,Page<Order>> map,
									   @PathVariable("isPay") String isPay){
		Page<Order> page = new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=? and order.isPay=?", new String[]{"0",isPay});
		commonService.pageQuery(Order.class, page, conditions);
		map.put("pageBean", page);
		if("1".equals(isPay)){
			return "admin/order/payOrderList";
		}
			
		return "admin/order/notPayOrderList";
		
	}
	
	/**
	 * 用来查询未发货或以完成的订单
	 * @param pageStartNum
	 * @param map
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(path="/listIsOrNotSendOrders/{pageStartNum}/{orderStatus}",method=RequestMethod.GET)
	public String listIsOrNotSendOrders(@PathVariable("pageStartNum") Integer pageStartNum,Map<String,Page<Order>> map,
									    @PathVariable("orderStatus") Integer orderStatus){
		Page<Order> page = new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=? and order.isPay=? and order.orderStatus=?", new String[]{"0","1",String.valueOf(orderStatus)});
		
		commonService.pageQuery(Order.class, page, conditions);
		
		map.put("pageBean", page);
		
		if(orderStatus==1){
			return "admin/order/sendOrderList";
			
		}
		
		return "admin/order/notSendOrderList";
		
	}
	
	/**
	 * 查询用户自己所下的订单
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/listOrdersByUserId/{pageStartNum}",method=RequestMethod.GET)
	public String listOrdersByUserId(HttpServletRequest request,Map<String,Object> map,
									 @PathVariable("pageStartNum") Integer pageStartNum){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "请登录后再进行相关操作!");
			return "forward:/login";
		}
		
		Page<Order> page = new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=? and order.user.userId=?", new String[]{"0",String.valueOf(user.getUserId())});
		
		commonService.pageQuery(Order.class, page, conditions);
		
		map.put("pageBean", page);
		
		return "client/order/list";
		
	}
	
	/**
	 * 查询用户自己所下的已付款或未付款的订单
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/listIsOrNotPayOrdersByUserId/{pageStartNum}/{isPay}",method=RequestMethod.GET)
	public String listIsOrNotPayOrdersByUserId(HttpServletRequest request,Map<String,Page<Order>> map,
			 								   @PathVariable("pageStartNum") Integer pageStartNum,
			 								   @PathVariable("isPay") String isPay){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login";
		}
		
		Page<Order> page = new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=? and order.user.userId=? and order.isPay=?", new String[]{"0",String.valueOf(user.getUserId()),isPay});
		
		commonService.pageQuery(Order.class, page, conditions);
		
		map.put("pageBean", page);
		
		if("1".equals(isPay)){
			return "client/order/payOrderList";
		}
		
		return "client/order/notPayOrderList";
		
	}
	
	/**
	 * 查询用户自己所下的未发货或已发货的订单
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/listIsOrNotSendOrdersByUserId/{pageStartNum}/{orderStatus}",method=RequestMethod.GET)
	public String listIsOrNotSendOrdersByUserId(HttpServletRequest request,Map<String,Page<Order>> map,
												@PathVariable("pageStartNum") Integer pageStartNum,
												@PathVariable("orderStatus") Integer orderStatus){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login";
		}
		
		Page<Order> page = new Page<>(pageStartNum, 3);
		Conditions conditions=new Conditions(" where order.del=? and order.user.userId=? and order.isPay=? and order.orderStatus=?", new String[]{"0",String.valueOf(user.getUserId()),"1",String.valueOf(orderStatus)});
		
		commonService.pageQuery(Order.class, page, conditions);
		
		map.put("pageBean", page);
		
		if(orderStatus==1){
			return "client/order/sendOrderList";
		}
		
		return "client/order/notSendOrderList";
		
	}
	
}
