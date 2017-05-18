package com.cab.graduation.web.handlers;

import java.util.ArrayList;
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
import com.cab.graduation.entities.Cart;
import com.cab.graduation.entities.Goods;
import com.cab.graduation.entities.Order;
import com.cab.graduation.entities.OrderItem;
import com.cab.graduation.entities.User;
import com.cab.graduation.service.AddressService;
import com.cab.graduation.service.CartService;
import com.cab.graduation.service.GoodsService;
import com.cab.graduation.service.OrderItemService;
import com.cab.graduation.service.OrderService;
import com.cab.graduation.utils.WebShopUtils;

@Controller
@RequestMapping(path="/cart")
public class CartHandler {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(path="/showMyCarts",method=RequestMethod.GET)
	public String showMyCarts(HttpServletRequest request,Map<String,Object> map){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "您还没有登录,请登录后再进行相关操作!");
			return "forward:/login";
		}
		if(user.getRank()==1){
			return "redirect:/manageCenter";
		}
		
		List<com.cab.graduation.entities.dto.Cart> cartResultList=new ArrayList<>();
		double totalPrice=0;
		List<Cart> carts=cartService.listByUserId(user.getUserId());
		if(carts.size()==0){
			map.put("isNull", true);
		}else{
			map.put("isNull", false);
		}
		for(Cart cart : carts){
			cartResultList.add(new com.cab.graduation.entities.dto.Cart(cart.getGoods(), cart));
			totalPrice+=cart.getcSubtotal();
		}
		
		map.put("cartResultList", cartResultList);
		map.put("totalPrice", totalPrice);
		
		return "carts";
		
	}
	
	@RequestMapping(path="/addCart",method=RequestMethod.POST)
	public String addCart(Cart cart,@RequestParam("goodId") String goodId,
			HttpServletRequest request,Map<String,String> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "您还没有登录,请登录后再进行相关操作!");
			return "forward:/login";
		}
		if(user.getRank()==1){
			return "redirect:/manageCenter";
		}
		Goods goods=goodsService.findGoodsById(goodId);
		cart.setGoods(goods);
		cart.setUser(user);
		
		Cart cart2=cartService.findCartByGoodIdAndUserId(Integer.valueOf(goodId), user.getUserId());
		
		if(cart2==null){
			cart.setcSubtotal(goods.getGoodSpikePrice()*cart.getcNum());
			cartService.addCart(cart);
		}else{
			cart2.setcNum(cart2.getcNum()+cart.getcNum());
			cart2.setcSubtotal(goods.getGoodSpikePrice()*cart2.getcNum());
			cartService.update(cart2);
		}
		return "redirect:/index";
	}
	
	/**
	 * 立即购买操作
	 * @param orderItem
	 * @param request
	 * @param goodId
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/buyNow",method=RequestMethod.POST)
	public String buyNow(OrderItem orderItem,HttpServletRequest request,
			@RequestParam("goodId") String goodId,Map<String,Object> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			map.put("message", "您还没有登录,请登录后再进行相关操作!");
			return "forward:/login";
		}
		if(user.getRank()==1){
			return "redirect:/manageCenter";
		}
		Goods goods=goodsService.findGoodsById(goodId);
		
		orderItem.setGoods(goods);
		
		orderItem.setOrderItemSubtotal(orderItem.getOrderItemNum()*goods.getGoodSpikePrice());
		
		com.cab.graduation.entities.dto.Cart cart=new com.cab.graduation.entities.dto.Cart(orderItem);
		List<com.cab.graduation.entities.dto.Cart> carts=new ArrayList<>();
		carts.add(cart);
		
		Address addr=addressService.findAddressByUserIdAnddefaultAddressStatus(user.getUserId(),"1");
		
		//防止用户刚注册后由于没有添加收货地址而导致的 Integer.valueOf(addrId) 类型转换异常
		if(addr==null){
			addr=new Address();
			addr.setAddrId(0);
		}
		
		//提交表单时所需要的数据
		map.put("goodIds", goods.getGoodId());
		map.put("oiNums", cart.getcNum());
		map.put("oiSubTotals", cart.getSubTotalPrice());
		map.put("orderTotalPrice", cart.getSubTotalPrice());
		map.put("address", addr);
		
		
		//订单信息
		map.put("carts", carts);
		
		//地址信息
		map.put("address", addr);
		
		//总计
		map.put("orderTotalPrice", cart.getSubTotalPrice());
		
		return "admin/order/confirmOrder";
//		
//		Order order = new Order();
//		order.setOrderNo(WebShopUtils.generateOrderSerialNum());
//		order.setIsPay("0");
//		order.setOrderStatus(0);
//		order.setOrderCreateTime(new Date());
//		order.setOrderTotalPrice(orderItem.getOrderItemSubtotal());
//		order.setUser(user);
//		//设置关联关系
//		order.getOrderItems().add(orderItem);
//		orderItem.setOrder(order);
//		
//		//执行保存操作
//		orderService.save(order);
//		orderItemService.save(orderItem);
//		
//		map.put("message", "订单已提交,感谢您的大力支持!");
		
//		return "admin/order/confirmOrder";
		
		
	}
	
	@RequestMapping(path="/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		Cart cart=cartService.findCartById(id);
		cartService.deleteCart(cart);
		return "redirect:/cart/showMyCarts";
	}

}
